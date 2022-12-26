package org.aneo.rover.model;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Objects;

/**
 *
 */
public final class Rover implements Cloneable {
    /**
     *
     */
    public static @NonNull final Rover DEFAULT = new Rover();

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
    public Rover() {
        this.location = new GridVector();
        this.direction = Direction.DEFAULT;
    }

    /**
     *
     */
    public Rover(@NonNull final Rover toCopy) {
        this.location = toCopy.location.clone();
        this.direction = toCopy.direction;
    }

    /**
     *
     */
    public Rover(@NonNull final GridVector coordinates, @NonNull final Direction direction) {
        this.location = coordinates.clone();
        this.direction = direction;
    }

    /**
     *
     */
    public Rover(final int x, final int y, @NonNull final Direction direction) {
        this.location = new GridVector(x, y);
        this.direction = direction;
    }

    /**
     *
     */
    public @NonNull Rover left() {
        this.direction = this.direction.left();
        return this;
    }

    /**
     *
     */
    public @NonNull Rover right() {
        this.direction = this.direction.right();
        return this;
    }

    /**
     *
     */
    public @NonNull Rover move() {
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
    public void set(final int x, final int y, @NonNull final Direction direction) {
        this.location.set(x, y);
        this.direction = direction;
    }

    /**
     *
     */
    public void copy(@NonNull final Rover toCopy) {
        this.location.copy(toCopy.location);
        this.direction = toCopy.direction;
    }

    /**
     *
     */
    @Override
    public @NonNull Rover clone() {
        return new Rover(this);
    }

    /**
     *
     */
    public @NonNull Rover clear() {
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

        if (other instanceof Rover) {
            @NonNull final Rover otherCoordinates = (Rover) other;

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
