package org.aneo.rover.model

import spock.lang.Specification

/**
 *
 */
class GridVectorSpecification extends Specification {
    /**
     *
     */
    def "GridVector() returns a zero vector" () {
        expect:
        new GridVector() == GridVector.ZERO
    }

    /**
     *
     */
    def "GridVector(final int, final int) returns a vector at the requested location" () {
        given:
            GridVector vector = new GridVector(5, 3)

        expect:
            vector.x == 5
            vector.y == 3
    }

    /**
     *
     */
    def "GridVector(@NonNull final GridVector) returns a copy of the given vector" () {
        given:
        GridVector origin = new GridVector(5, 3)

        expect:
        new GridVector(origin) == origin
        new GridVector(origin) !== origin
    }

    /**
     *
     */
    def "#set(final int, final int) updates the vector location" () {
        given:
        GridVector origin = new GridVector(5, 3)

        when:
        origin.set(2, 9)

        then:
        origin == new GridVector(2, 9)
    }

    /**
     *
     */
    def "#copy(@NonNull final GridVector) copy the given vector" () {
        given:
        GridVector origin = new GridVector(5, 3)
        GridVector copy = new GridVector(6, 9)

        when:
        copy.copy(origin)

        then:
        copy == origin
        copy !== origin
    }

    /**
     *
     */
    def "#add(final int, final int) adds the given constants" () {
        given:
        GridVector vector = new GridVector(5, 3)

        when:
        vector.add(2, 9)

        then:
        vector == new GridVector(7, 12)
    }

    /**
     *
     */
    def "#add(@NonNull final GridVector) adds the given vector" () {
        given:
        GridVector vector = new GridVector(5, 3)

        when:
        vector.add(new GridVector(2, 9))

        then:
        vector == new GridVector(7, 12)
    }

    /**
     *
     */
    def "#clear() resets the vector to zero" () {
        given:
        GridVector vector = new GridVector(5, 3)

        when:
        vector.clear()

        then:
        vector == GridVector.ZERO
    }

    /**
     *
     */
    def "#clone() returns a copy of the vector" () {
        given:
        GridVector vector = new GridVector(5, 3)

        when:
        GridVector copy = vector.clone()

        then:
        copy == vector
        copy !== vector
    }

    /**
     *
     */
    def "#equals(@Nullable final Object) returns false if called with a null reference" () {
        given:
        GridVector vector = new GridVector(5, 3)

        expect:
        vector != null
    }

    /**
     *
     */
    def "#equals(@Nullable final Object) returns true if called over itself" () {
        given:
        GridVector vector = new GridVector(5, 3)

        expect:
        vector == vector
    }

    /**
     *
     */
    def "#equals(@Nullable final Object) returns false if called on a non-vector object" () {
        given:
        GridVector vector = new GridVector(5, 3)

        expect:
        vector != new Object()
    }

    /**
     *
     */
    def "#equals(@Nullable final Object) returns true if both instances are equals" () {
        given:
        GridVector vector = new GridVector(5, 3)

        expect:
        vector == new GridVector(5, 3)
    }

    /**
     *
     */
    def "#equals(@Nullable final Object) returns false if both instances are not equals" () {
        given:
        GridVector vector = new GridVector(5, 3)

        expect:
        vector != new GridVector(2, 1)
    }
}
