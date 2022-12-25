package org.aneo.rover.model

import spock.lang.Specification

/**
 *
 */
class DirectionSpecification extends Specification {
    /**
     *
     */
    def "#right() returns the resulting direction after a clockwise 90 degrees rotation" () {
        expect:
        Direction.NORTH.right() == Direction.EAST
            Direction.EAST.right() == Direction.SOUTH
            Direction.SOUTH.right() == Direction.WEST
            Direction.WEST.right() == Direction.NORTH
    }

    /**
     *
     */
    def "#left() returns the resulting direction after a counter-clockwise 90 degrees rotation" () {
        expect:
        Direction.NORTH.left() == Direction.WEST
        Direction.EAST.left() == Direction.NORTH
        Direction.SOUTH.left() == Direction.EAST
        Direction.WEST.left() == Direction.SOUTH
    }

    /**
     *
     */
    def "#negate() returns the opposing direction" () {
        expect:
        Direction.NORTH.negate() == Direction.SOUTH
        Direction.EAST.negate() == Direction.WEST
        Direction.SOUTH.negate() == Direction.NORTH
        Direction.WEST.negate() == Direction.EAST
    }
}
