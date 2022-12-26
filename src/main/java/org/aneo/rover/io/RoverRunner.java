package org.aneo.rover.io;

import org.aneo.rover.model.Direction;
import org.aneo.rover.model.GridVector;
import org.aneo.rover.model.RoverAction;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Arrays;

public class RoverRunner implements RoverGrammarListener {
    /**
     *
     */
    private final @NonNull RoverContext _context;

    /**
     *
     */
    // Antlr catch errors on throw due to a side effect and then try to close each already opened rule including
    // the rule in fault. To ignore this dubious implementation, the listener does manage the error internally and
    // then allow the user to extract it after the parsing operation.
    private @Nullable Error _error;

    /**
     *
     */
    public RoverRunner() {
        _context = new RoverContext();
        _error = null;
    }

    @Override
    public void enterStream(final RoverGrammarParser.@NonNull StreamContext streamContext) {
        _error = null;
    }

    @Override
    public void exitStream(final RoverGrammarParser.@NonNull StreamContext streamContext) {
        _context.clear();
    }

    @Override
    public void enterCommand(final RoverGrammarParser.@NonNull CommandContext commandContext) {

    }

    @Override
    public void exitCommand(final RoverGrammarParser.@NonNull CommandContext commandContext) {

    }

    @Override
    public void enterGrid(final RoverGrammarParser.@NonNull GridContext gridContext) {
    }

    @Override
    public void exitGrid(final RoverGrammarParser.@NonNull GridContext gridContext) {
        if (_error != null) return;

        handleGridColumns(gridContext);
        handleGridRows(gridContext);

        if (_context.rows <= 0 || _context.columns <= 0) {
            throw fail(
                "Trying to declare an empty grid " + location(gridContext) + "."
            );
        }

        _context.rover.location.clamp(_context.columns, _context.rows);
    }

    // @TODO Prefer an approach without any string instantiation.
    private void handleGridColumns(final RoverGrammarParser.@NonNull GridContext gridContext) {
        try {
            _context.columns = Integer.parseInt(gridContext.columns.getText());
        } catch (@NonNull final NumberFormatException exception) {
            throwInvalidIntegerErrorAt(gridContext.columns, exception);
        }
    }

    // @TODO Prefer an approach without any string instantiation.
    private void handleGridRows(final RoverGrammarParser.@NonNull GridContext gridContext) {
        try {
            _context.rows = Integer.parseInt(gridContext.rows.getText());
        } catch (@NonNull final NumberFormatException exception) {
            throwInvalidIntegerErrorAt(gridContext.rows, exception);
        }
    }

    @Override
    public void enterRover(final RoverGrammarParser.@NonNull RoverContext roverContext) {
    }

    @Override
    public void exitRover(final RoverGrammarParser.@NonNull RoverContext roverContext) {
        if (_error != null) return;

        handleRoverRow(roverContext);
        handleRoverColumn(roverContext);
        handleRoverDirection(roverContext);
    }

    /**
     *
     */
    // @TODO Prefer an approach without any string instantiation.
    private void handleRoverRow(final RoverGrammarParser.@NonNull RoverContext roverContext) {
        try {
            _context.rover.location.y = Integer.parseInt(roverContext.row.getText());
        } catch (@NonNull final NumberFormatException exception) {
            throwInvalidIntegerErrorAt(roverContext.row, exception);
        }

        if (_context.rover.location.y > _context.rows) {
            throwIllegalRoverLocationAt(roverContext.row);
        }
    }

    /**
     *
     */
    // @TODO Prefer an approach without any string instantiation.
    private void handleRoverColumn(final RoverGrammarParser.@NonNull RoverContext roverContext) {
        try {
            _context.rover.location.x = Integer.parseInt(roverContext.column.getText());
        } catch (@NonNull final NumberFormatException exception) {
            throwInvalidIntegerErrorAt(roverContext.column, exception);
        }

        if (_context.rover.location.x > _context.columns) {
            throwIllegalRoverLocationAt(roverContext.column);
        }
    }

    /**
     *
     */
    private void handleRoverDirection(final RoverGrammarParser.@NonNull RoverContext roverContext) {
        switch (roverContext.direction.getType()) {
            case RoverGrammarParser.NORTH:
                _context.rover.direction = Direction.NORTH;
                break;
            case RoverGrammarParser.EAST:
                _context.rover.direction = Direction.EAST;
                break;
            case RoverGrammarParser.SOUTH:
                _context.rover.direction = Direction.SOUTH;
                break;
            case RoverGrammarParser.WEST:
                _context.rover.direction = Direction.WEST;
                break;
            default:
                throw fail(
                        "Unable to map the token " + roverContext.direction.toString() + " " + location(roverContext) +
                        " to one of the following directions " + Arrays.toString(Direction.values()) + ". You may have " +
                        "a problem with your AST."
                );
        }
    }

    @Override
    public void enterActions(final RoverGrammarParser.@NonNull ActionsContext actionsContext) {

    }

    @Override
    public void exitActions(final RoverGrammarParser.@NonNull ActionsContext actionsContext) {
        if (_error != null) return;

        System.out.println(
                _context.rover.location.x + " " +
                        _context.rover.location.y + " " +
                        _context.rover.direction.toString().charAt(0)
        );
    }

    @Override
    public void enterAction(final RoverGrammarParser.@NonNull ActionContext actionContext) {
    }

    @Override
    public void exitAction(final RoverGrammarParser.@NonNull ActionContext actionContext) {
        if (_error != null) return;

        handleRoverAction(actionContext).apply(_context.rover);

        @NonNull final GridVector location = _context.rover.location;

        if (location.x < 0 || location.y < 0 || location.x > _context.columns || location.y > _context.rows) {
            throwIllegalRoverLocationAt(actionContext);
        }
    }

    /**
     *
     */
    private @NonNull RoverAction handleRoverAction(final RoverGrammarParser.@NonNull ActionContext actionContext) {
        @NonNull final Token token = actionContext.getStart();

        switch (token.getType()) {
            case RoverGrammarParser.MOVE:
                return RoverAction.MOVE;
            case RoverGrammarParser.LEFT:
                return RoverAction.LEFT;
            case RoverGrammarParser.RIGHT:
                return RoverAction.RIGHT;
            default:
                throw fail(
                    "Unable to map the token " + token + " " + location(token) +
                    " to one of the following actions " + Arrays.toString(RoverAction.values()) + ". You may have " +
                    "a problem with your AST."
                );
        }
    }

    @Override
    public void visitTerminal(final @NonNull TerminalNode node) {

    }

    @Override
    public void visitErrorNode(final @NonNull ErrorNode node) {

    }

    @Override
    public void enterEveryRule(final @NonNull ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(final @NonNull ParserRuleContext parserRuleContext) {

    }

    /**
     *
     */
    private void throwInvalidIntegerErrorAt(@NonNull final Token token, @NonNull final NumberFormatException reason) {
        throw fail(
                "Unable to parse the token " + token + " " + location(token) +
                        " as a java integer."
        );
    }

    /**
     *
     */
    private void throwIllegalRoverLocationAt(@NonNull final Token token) {
        throw fail(
                "Illegal rover location (" + _context.rover.location.x + ", " + _context.rover.location.y +
                        ") defined " + location(token) + " as the grid only contains " + _context.columns +
                        " column(s) and " + _context.rows + " row(s)."
        );
    }

    /**
     *
     */
    private void throwIllegalRoverLocationAt(@NonNull final ParserRuleContext rule) {
        throw fail(
                "Illegal rover location (" + _context.rover.location.x + ", " + _context.rover.location.y +
                ") defined " + location(rule) + " as the grid only contains " + _context.columns +
                " column(s) and " + _context.rows + " row(s)."
        );
    }

    /**
     *
     */
    private @NonNull String location(@NonNull final ParserRuleContext rule) {
        return (
                "from line " + rule.getStart().getLine() +
                " at index " + rule.getStart().getStartIndex() +
                " to line " + rule.getStop().getLine() +
                " at index " + rule.getStop().getStopIndex()
        );
    }

    /**
     *
     */
    private @NonNull String location(@NonNull final Token token) {
        return (
                "from line " + token.getLine() +
                " at index " + token.getStartIndex() +
                " to index " + token.getStopIndex()
        );
    }

    /**
     *
     */
    private @NonNull Error fail(@NonNull final String message) {
        if (_error != null) {
            throw new Error("Error cascade due to an unmanaged error state.");
        }

        _error = new Error(message);
        return  _error;
    }

    /**
     *
     */
    public @Nullable Error getError() {
        return _error;
    }
}
