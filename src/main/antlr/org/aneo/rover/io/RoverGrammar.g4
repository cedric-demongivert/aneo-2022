grammar RoverGrammar;

@header {
    package org.aneo.rover.io;
}

stream: SPACES? EOF |
        SPACES? command (SPACES command)* SPACES? EOF;

command: actions | rover | grid;

grid: columns=INT SPACES rows=INT;
rover: column=INT SPACES row=INT SPACES direction=(NORTH | EAST | SOUTH | WEST);
actions: action (SPACES? action)*;

action:
      MOVE
    | LEFT
    | RIGHT
    ;

INT: [0-9]+;

NORTH: 'N';
EAST: 'E';
SOUTH: 'S';
WEST: 'W';

MOVE: 'M';
LEFT: 'L';
RIGHT: 'R';

SPACES: [ \t\f\n\r]+;

