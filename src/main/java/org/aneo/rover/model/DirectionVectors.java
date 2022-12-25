package org.aneo.rover.model;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * @TODO Use static type checking with annotation to enforce immutability.
 * @TODO Or create an immutable implementation if needed.
 */
public final class DirectionVectors {
    /**
     *
     */
    public static final @NonNull GridVector NORTH = new GridVector(0, 1);

    /**
     *
     */
    public static final @NonNull GridVector EAST = new GridVector(1, 0);

    /**
     *
     */
    public static final @NonNull GridVector SOUTH = new GridVector(0, -1);

    /**
     *
     */
    public static final @NonNull GridVector WEST = new GridVector(-1, 0);

    /**
     *
     */
    public static final @NonNull GridVector[] VALUES = new GridVector[] {
            NORTH,
            EAST,
            SOUTH,
            WEST
    };

    /**
     *
     */
    public static @NonNull GridVector get(@NonNull final Direction direction) {
        return VALUES[direction.ordinal()];
    }
}
