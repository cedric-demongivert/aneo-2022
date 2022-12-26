package org.aneo.rover.io;

import org.aneo.rover.model.Rover;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Objects;

/**
 *
 */
public final class RoverContext {
    /**
     * Readonly
     */
    public static final @NonNull RoverContext DEFAULT = new RoverContext();

    /**
     *
     */
    public static final int DEFAULT_COLUMNS = Integer.MAX_VALUE;

    /**
     *
     */
    public static final int DEFAULT_ROWS = Integer.MAX_VALUE;

    /**
     *
     */
    public int columns;

    /**
     *
     */
    public int rows;

    /**
     *
     */
    public final @NonNull Rover rover;

    /**
     *
     */
    public RoverContext () {
        columns = DEFAULT_COLUMNS;
        rows = DEFAULT_ROWS;
        rover = new Rover();
    }

    /**
     *
     */
    public RoverContext(@NonNull final RoverContext toCopy) {
        columns = toCopy.columns;
        rows = toCopy.rows;
        rover = toCopy.rover.clone();
    }

    /**
     *
     */
    public @NonNull RoverContext copy(@NonNull final RoverContext toCopy) {
        columns = toCopy.columns;
        rows = toCopy.rows;
        rover.copy(toCopy.rover);
        return this;
    }

    /**
     *
     */
    public @NonNull RoverContext clone() {
        return new RoverContext(this);
    }

    /**
     *
     */
    public @NonNull RoverContext clear() {
        columns = DEFAULT_COLUMNS;
        rows = DEFAULT_ROWS;
        rover.clear();
        return this;
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(@Nullable final Object other) {
        if (other == null) return false;
        if (other == this) return true;

        if (other instanceof RoverContext) {
            @NonNull final RoverContext otherContext = (RoverContext) other;

            return (
                    otherContext.columns == columns &&
                    otherContext.rows == rows &&
                    otherContext.rover.equals(rover)
            );
        }

        return false;
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(columns, rows, rover);
    }
}
