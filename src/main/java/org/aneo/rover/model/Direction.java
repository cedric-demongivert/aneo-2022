package org.aneo.rover.model;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Western cardinal directions.
 */
public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    /**
     *
     */
    public static @NonNull final Direction DEFAULT = Direction.values()[0];

    /**
     * @return The resulting direction after a clockwise 90 degrees rotation.
     */
    public @NonNull Direction right() {
        return Direction.values()[(this.ordinal() + 1) % 4];
    }

    /**
     * @return The resulting direction after a counter-clockwise 90 degrees rotation.
     */
    public @NonNull Direction left() {
        return Direction.values()[(this.ordinal() + 3) % 4];
    }

    /**
     * @return The opposite direction.
     */
    public @NonNull Direction negate() {
        return Direction.values()[(this.ordinal() + 2) % 4];
    }
}
