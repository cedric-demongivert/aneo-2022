package org.aneo.rover.model

import spock.lang.Specification

/**
 *
 */
class RoverActionSpecification extends Specification {
    /**
     *
     */
    def "#apply(@NonNull final Rover) allows to move a rover" () {
        given:
        Rover origin = new Rover(5, 3, Direction.EAST)
        Rover result = origin.clone()

        expect:
        RoverAction.MOVE.apply(result) == origin.move()
    }

    /**
     *
     */
    def "#apply(@NonNull final Rover) allows to rotate a rover to the left" () {
        given:
        Rover origin = new Rover(5, 3, Direction.EAST)
        Rover result = origin.clone()

        expect:
        RoverAction.LEFT.apply(result) == origin.left()
    }

    /**
     *
     */
    def "#apply(@NonNull final Rover) allows to rotate a rover to the right" () {
        given:
        Rover origin = new Rover(5, 3, Direction.EAST)
        Rover result = origin.clone()

        expect:
        RoverAction.RIGHT.apply(result) == origin.right()
    }
}
