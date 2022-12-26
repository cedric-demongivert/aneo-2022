package org.aneo.rover.model;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Objects;

/**
 * Any external Vector2Int implementation can replace this class.
 */
public final class GridVector implements Cloneable {
    /**
     *
     */
    public int x;

    /**
     *
     */
    public int y;

    /**
     *
     */
    public static final @NonNull GridVector ZERO = new GridVector();

    /**
     *
     */
    public GridVector() {
        this(0, 0);
    }

    /**
     *
     */
    public GridVector(@NonNull final GridVector toCopy) {
        this(toCopy.x, toCopy.y);
    }

    /**
     *
     */
    public GridVector(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     */
    public @NonNull GridVector set(final int x, final int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    /**
     *
     */
    public @NonNull GridVector copy(@NonNull final GridVector toCopy) {
        this.x = toCopy.x;
        this.y = toCopy.y;
        return this;
    }

    /**
     *
     */
    public @NonNull GridVector clamp(final int maxX, final int maxY) {
        this.x = Math.min(x, maxX);
        this.y = Math.min(y, maxY);
        return this;
    }

    /**
     *
     */
    public @NonNull GridVector add(final int x, final int y) {
        this.x += x;
        this.y += y;
        return this;
    }

    /**
     *
     */
    public @NonNull GridVector add(@NonNull final GridVector toAdd) {
        this.x += toAdd.x;
        this.y += toAdd.y;
        return this;
    }

    /**
     *
     */
    public @NonNull GridVector clear() {
        this.x = 0;
        this.y = 0;
        return this;
    }

    /**
     *
     */
    @Override
    public @NonNull GridVector clone() {
        return new GridVector(this);
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(@Nullable final Object other) {
        if (other == null) return false;
        if (other == this) return true;

        if (other instanceof GridVector) {
            @NonNull final GridVector otherCoordinates = (GridVector) other;

            return (
                otherCoordinates.x == this.x &&
                otherCoordinates.y == this.y
            );
        }

        return false;
    }

    /**
     * @TODO Use of a 2D to 1D projection instead.
     *
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    /**
     * @see Object#toString()
     */
    @Override
    public @NonNull String toString() {
        return super.toString() + "[" + this.x + ", " + this.y + "]";
    }
}
