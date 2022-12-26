package org.aneo.rover.model

import spock.lang.Specification

/**
 *
 */
class RoverSpecification extends Specification {
    /**
     *
     */
    def "Rover() returns a default rover" () {
        expect:
        new Rover().location == GridVector.ZERO
        new Rover().direction == Direction.DEFAULT
    }

    /**
     *
     */
    def "Rover(final int, final int, @NonNull final Direction) returns the requested rover" () {
        given:
        Rover origin = new Rover(5, 3, Direction.EAST)

        expect:
        origin.location == new GridVector(5, 3)
        origin.direction == Direction.EAST
    }

    /**
     *
     */
    def "Rover(@NonNull final Rover) returns a copy of the given rover" () {
        given:
        Rover origin = new Rover(5, 3, Direction.EAST)

        expect:
        new Rover(origin) == origin
        new Rover(origin) !== origin
    }

    /**
     *
     */
    def "#copy(@NonNull final Rover) copy the given rover" () {
        given:
        Rover origin = new Rover(5, 3, Direction.EAST)
        Rover copy = new Rover(2, -1, Direction.SOUTH)

        when:
        copy.copy(origin)

        then:
        copy == origin
        copy !== origin
    }

    /**
     *
     */
    def "#left() rotates the rover to the left" () {
        given:
        Rover origin = new Rover(5, 3, Direction.EAST)

        when:
        origin.left()

        then:
        origin.direction == Direction.EAST.left()
    }

    /**
     *
     */
    def "#right() rotates the rover to the right" () {
        given:
        Rover origin = new Rover(5, 3, Direction.EAST)

        when:
        origin.right()

        then:
        origin.direction == Direction.EAST.right()
    }

    /**
     *
     */
    def "#move() moves the rover forward" () {
        given:
        GridVector origin = new GridVector(5, 3)
        List<Rover> rovers = []

        for (Direction direction : Direction.values()) {
            rovers.add(new Rover(origin, direction))
        }

        when:
        for (Rover rover : rovers) {
            rover.move()
        }

        then:
        GridVector destination = new GridVector()

        for (Rover rover : rovers) {
            rover.location == destination.copy(origin).add(DirectionVectors.get(rover.direction))
        }
    }

    /**
     *
     */
    def "#move() does not update the direction" () {
        given:
        GridVector origin = new GridVector(5, 3)
        List<Rover> rovers = []

        for (Direction direction : Direction.values()) {
            rovers.add(new Rover(origin, direction))
        }

        when:
        for (Rover rover : rovers) {
            rover.move()
        }

        then:
        for (Direction direction : Direction.values()) {
            rovers[direction.ordinal()].direction == direction
        }
    }

    /**
     *
     */
    def "#clear() resets the rover to default" () {
        given:
        Rover rover = new Rover(5, 3, Direction.EAST)

        when:
        rover.clear()

        then:
        rover == Rover.DEFAULT
    }

    /**
     *
     */
    def "#clone() returns a copy of the rover" () {
        given:
        Rover rover = new Rover(5, 3, Direction.EAST)

        when:
        Rover copy = rover.clone()

        then:
        copy == rover
        copy !== rover
    }

    /**
     *
     */
    def "#equals(@Nullable final Object) returns false if called with a null reference" () {
        given:
        Rover rover = new Rover(5, 3, Direction.EAST)

        expect:
        rover != null
    }

    /**
     *
     */
    def "#equals(@Nullable final Object) returns true if called over itself" () {
        given:
        Rover rover = new Rover(5, 3, Direction.EAST)

        expect:
        rover == rover
    }

    /**
     *
     */
    def "#equals(@Nullable final Object) returns false if called on an object of another type" () {
        given:
        Rover rover = new Rover(5, 3, Direction.EAST)

        expect:
        rover != new Object()
    }

    /**
     *
     */
    def "#equals(@Nullable final Object) returns true if both instances are equals" () {
        given:
        Rover rover = new Rover(5, 3, Direction.EAST)

        expect:
        rover == new Rover(5, 3, Direction.EAST)
    }

    /**
     *
     */
    def "#equals(@Nullable final Object) returns false if both instances are not equals" () {
        given:
        Rover rover = new Rover(5, 3, Direction.EAST)

        expect:
        rover != new Rover(5, 2, Direction.EAST)
    }
}
