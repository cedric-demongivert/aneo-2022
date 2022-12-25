package org.aneo.rover.model;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Objects;

/**
 *
 */
public final class RoverCoordinates implements Cloneable {
    /**
     *
     */
    public static @NonNull final RoverCoordinates DEFAULT = new RoverCoordinates();

    /**
     *
     */
    public final @NonNull GridVector location;

    /**
     *
     */
    public @NonNull Direction direction;

    /**
     *
     */
    public RoverCoordinates() {
        this.location = new GridVector();
        this.direction = Direction.DEFAULT;
    }

    /**
     *
     */
    public RoverCoordinates(@NonNull final RoverCoordinates toCopy) {
        this.location = toCopy.location.clone();
        this.direction = toCopy.direction;
    }

    /**
     *
     */
    public RoverCoordinates(@NonNull final GridVector coordinates, @NonNull final Direction direction) {
        this.location = coordinates.clone();
        this.direction = direction;
    }

    /**
     *
     */
    public RoverCoordinates(final int x, final int y, @NonNull final Direction direction) {
        this.location = new GridVector(x, y);
        this.direction = direction;
    }

    /**
     *
     */
    public @NonNull RoverCoordinates left() {
        this.direction = this.direction.left();
        return this;
    }

    /**
     *
     */
    public @NonNull RoverCoordinates right() {
        this.direction = this.direction.right();
        return this;
    }

    /**
     *
     */
    public @NonNull RoverCoordinates negate() {
        this.direction = this.direction.negate();
        return this;
    }

    /**
     *
     */
    public @NonNull RoverCoordinates move() {
        this.location.add(DirectionVectors.get(this.direction));
        return this;
    }

    /**
     *
     */
    public void set(@NonNull final GridVector grid, @NonNull final Direction direction) {
        this.location.copy(grid);
        this.direction = direction;
    }

    /**
     *
     */
    public void copy(@NonNull final RoverCoordinates toCopy) {
        this.location.copy(toCopy.location);
        this.direction = toCopy.direction;
    }

    /**
     *
     */
    @Override
    public @NonNull RoverCoordinates clone() {
        return new RoverCoordinates(this);
    }

    /**
     *
     */
    public @NonNull RoverCoordinates clear() {
        this.location.clear();
        this.direction = Direction.DEFAULT;
        return this;
    }

    /**
     * @see Object#equals(Object) 
     */
    @Override
    public boolean equals(@Nullable final Object other) {
        if (other == null) return false;
        if (other == this) return true;

        if (other instanceof RoverCoordinates) {
            @NonNull final RoverCoordinates otherCoordinates = (RoverCoordinates) other;

            return (
                otherCoordinates.direction == this.direction &&
                otherCoordinates.location.equals(this.location)
            );
        }

        return false;
    }

    /**
     * @see Object#hashCode() 
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.direction, this.location);
    }

    /**
     * @see Object#toString()
     */
    @Override
    public @NonNull String toString() {
        return (
                super.toString() + "[" +
                        this.location.x + ", " +
                        this.location.y + ", " +
                        this.direction.toString() +
                        "]"
        );
    }
}
