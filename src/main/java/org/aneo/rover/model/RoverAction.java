package org.aneo.rover.model;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Set of all possible rover commands.
 */
public enum RoverAction {
    /**
     *
     */
    MOVE,
    RIGHT,
    LEFT;

    /**
     *
     */
    public @NonNull Rover apply(@NonNull final Rover rover) {
        switch (this) {
            case MOVE: return rover.move();
            case RIGHT: return rover.right();
            case LEFT: return rover.left();
        }

        throw new Error(
            "Unhandled action type " + this.toString() + ". This may be the result " +
            "of the addition of a new command type, please add the corresponding " +
            "procedure to this method's switch."
        );
    }
}
