package org.aneo.rover.io;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public class Symbol {
    /**
     *
     */
    public char symbol;

    /**
     *
     */
    public int row;

    /**
     *
     */
    public int column;

    /**
     *
     */
    public Symbol() {
        this.symbol = 0;
        this.row = 0;
        this.column = 0;
    }

    /**
     *
     */
    public Symbol(final char symbol, final int row, final int column) {
        this.symbol = symbol;
        this.row = row;
        this.column = column;
    }

    /**
     *
     */
    public boolean equals(@Nullable final Object other) {
        if (other == null) return false;
        if (other == this) return true;

        if (other instanceof Symbol) {
            @NonNull final Symbol otherSymbol = (Symbol) other;

            return (
              otherSymbol.symbol == this.symbol &&
              otherSymbol.row == this.row &&
              otherSymbol.column == this.column
            );
        }

        return false;
    }

    /**
     *
     */
    public
}
