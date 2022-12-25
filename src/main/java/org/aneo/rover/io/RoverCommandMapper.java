package org.aneo.rover.io;

import org.aneo.rover.model.RoverCommand;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Arrays;

public class RoverCommandMapper {
    /**
     *
     */
    public @NonNull RoverCommand map(final char symbol) {
        switch (symbol) {
            case 'L': return RoverCommand.LEFT;
            case 'R': return RoverCommand.RIGHT;
            case 'M': return RoverCommand.MOVE;
            default:
                throw new IllegalArgumentException(
                  "Unable to map symbol '" + symbol + "' to one of the following " +
                  "rover command " + Arrays.toString(RoverCommand.values()) + " because the given " +
                  "symbol is not a valid rover command symbol (L, R or M)."
                );
        }
    }

}
