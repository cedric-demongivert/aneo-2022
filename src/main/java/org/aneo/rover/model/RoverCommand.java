package org.aneo.rover.model;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Set of all possible rover commands.
 */
public enum RoverCommand {
    /**
     *
     */
    MOVE,
    RIGHT,
    LEFT;

    /**
     *
     */
    public @NonNull RoverCoordinates apply(@NonNull final RoverCoordinates coordinates) {
        switch (this) {
            case MOVE: return coordinates.move();
            case RIGHT: return coordinates.right();
            case LEFT: return coordinates.left();
        }

        throw new Error(
            "Unhandled command type " + this.toString() + ". This may be the result " +
            "of the addition of a new command type, please add the corresponding " +
            "procedure to this method's switch."
        );
    }
}
