package org.aneo.rover.model

import spock.lang.Specification

/**
 *
 */
class RoverCoordinatesSpecification extends Specification {
    /**
     *
     */
    def "RoverCoordinates() returns a default set of coordinates" () {
        expect:
        new RoverCoordinates().location == GridVector.ZERO
        new RoverCoordinates().direction == Direction.DEFAULT
    }

    /**
     *
     */
    def "RoverCoordinates(final int, final int, @NonNull final Direction) returns the given coordinates" () {
        given:
        RoverCoordinates origin = new RoverCoordinates(5, 3, Direction.EAST)

        expect:
        origin.location == new GridVector(5, 3)
        origin.direction == Direction.EAST
    }

    /**
     *
     */
    def "RoverCoordinates(@NonNull final RoverCoordinates) returns a copy with the given coordinates" () {
        given:
        RoverCoordinates origin = new RoverCoordinates(5, 3, Direction.EAST)

        expect:
        new RoverCoordinates(origin) == origin
        new RoverCoordinates(origin) !== origin
    }

    /**
     *
     */
    def "#copy(@NonNull final RoverCoordinates) copy the given coordinates" () {
        given:
        RoverCoordinates origin = new RoverCoordinates(5, 3, Direction.EAST)
        RoverCoordinates copy = new RoverCoordinates(2, -1, Direction.SOUTH)

        when:
        copy.copy(origin)

        then:
        copy == origin
        copy !== origin
    }

    /**
     *
     */
    def "#clear() resets the coordinates to default" () {
        given:
        RoverCoordinates coordinates = new RoverCoordinates(5, 3, Direction.EAST)

        when:
        coordinates.clear()

        then:
        coordinates == RoverCoordinates.DEFAULT
    }

    /**
     *
     */
    def "#clone() return a copy of the coordinates" () {
        given:
        RoverCoordinates coordinates = new RoverCoordinates(5, 3, Direction.EAST)

        when:
        RoverCoordinates copy = coordinates.clone()

        then:
        copy == coordinates
        copy !== coordinates
    }

    /**
     *
     */
    def "#equals(@Nullable final Object) return false if called with a null reference" () {
        given:
        RoverCoordinates coordinates = new RoverCoordinates(5, 3, Direction.EAST)

        expect:
        coordinates != null
    }

    /**
     *
     */
    def "#equals(@Nullable final Object) return true if called over itself" () {
        given:
        RoverCoordinates coordinates = new RoverCoordinates(5, 3, Direction.EAST)

        expect:
        coordinates == coordinates
    }

    /**
     *
     */
    def "#equals(@Nullable final Object) return false if called on an object of another type" () {
        given:
        RoverCoordinates coordinates = new RoverCoordinates(5, 3, Direction.EAST)

        expect:
        coordinates != new Object()
    }

    /**
     *
     */
    def "#equals(@Nullable final Object) return true if both instances are equals" () {
        given:
        RoverCoordinates coordinates = new RoverCoordinates(5, 3, Direction.EAST)

        expect:
        coordinates == new RoverCoordinates(5, 3, Direction.EAST)
    }

    /**
     *
     */
    def "#equals(@Nullable final Object) return true if both instances are not equals" () {
        given:
        RoverCoordinates coordinates = new RoverCoordinates(5, 3, Direction.EAST)

        expect:
        coordinates != new RoverCoordinates(5, 2, Direction.EAST)
    }
}
