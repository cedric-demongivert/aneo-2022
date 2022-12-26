package org.aneo.rover.io

import org.aneo.rover.model.Direction
import org.aneo.rover.model.DirectionVectors
import org.aneo.rover.model.GridVector
import org.aneo.rover.model.Rover
import spock.lang.Specification

/**
 *
 */
class RoverContextSpecification extends Specification {
    /**
     *
     */
    def "RoverContext() returns a default context" () {
        expect:
        new RoverContext().rows == RoverContext.DEFAULT_ROWS
        new RoverContext().columns == RoverContext.DEFAULT_COLUMNS
        new RoverContext().rover == Rover.DEFAULT
    }

    /**
     *
     */
    def "RoverContext(@NonNull final RoverContext) returns a copy of the given context" () {
        given:
        RoverContext origin = new RoverContext()
        origin.rows = 6
        origin.columns = 9
        origin.rover.set(5, 3, Direction.WEST)

        expect:
        new RoverContext(origin) == origin
        new RoverContext(origin) !== origin
    }

    /**
     *
     */
    def "#copy(@NonNull final RoverContext) copy the given context" () {
        given:
        RoverContext origin = new RoverContext()
        origin.rows = 6
        origin.columns = 9
        origin.rover.set(5, 3, Direction.WEST)

        RoverContext copy = new RoverContext()

        when:
        copy.copy(origin)

        then:
        copy == origin
        copy !== origin
    }

    /**
     *
     */
    def "#clear() resets the context to its default state" () {
        given:
        RoverContext context = new RoverContext()
        context.rows = 6
        context.columns = 9
        context.rover.set(5, 3, Direction.WEST)

        when:
        context.clear()

        then:
        context == RoverContext.DEFAULT
    }

    /**
     *
     */
    def "#clone() returns a copy of the context" () {
        given:
        RoverContext context = new RoverContext()
        context.rows = 6
        context.columns = 9
        context.rover.set(5, 3, Direction.WEST)

        when:
        RoverContext copy = context.clone()

        then:
        copy == context
        copy !== context
    }

    /**
     *
     */
    def "#equals(@Nullable final Object) returns false if called with a null reference" () {
        given:
        RoverContext context = new RoverContext()
        context.rows = 6
        context.columns = 9
        context.rover.set(5, 3, Direction.WEST)

        expect:
        context != null
    }

    /**
     *
     */
    def "#equals(@Nullable final Object) returns true if called over itself" () {
        given:
        RoverContext context = new RoverContext()
        context.rows = 6
        context.columns = 9
        context.rover.set(5, 3, Direction.WEST)

        expect:
        context == context
    }

    /**
     *
     */
    def "#equals(@Nullable final Object) returns false if called on an object of another type" () {
        given:
        RoverContext context = new RoverContext()
        context.rows = 6
        context.columns = 9
        context.rover.set(5, 3, Direction.WEST)

        expect:
        context != new Object()
    }

    /**
     *
     */
    def "#equals(@Nullable final Object) returns true if both instances are equals" () {
        given:
        RoverContext context = new RoverContext()
        context.rows = 6
        context.columns = 9
        context.rover.set(5, 3, Direction.WEST)

        RoverContext copy = new RoverContext()
        copy.rows = 6
        copy.columns = 9
        copy.rover.set(5, 3, Direction.WEST)

        expect:
        copy == context
    }

    /**
     *
     */
    def "#equals(@Nullable final Object) returns false if both instances are not equals" () {
        given:
        RoverContext context = new RoverContext()
        context.rows = 6
        context.columns = 9
        context.rover.set(5, 3, Direction.WEST)

        RoverContext different = new RoverContext()
        different.rows = 6
        different.columns = 9
        different.rover.set(5, 1, Direction.WEST)

        expect:
        context != different
    }
}
