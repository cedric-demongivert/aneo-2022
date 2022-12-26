package org.aneo.rover;

import org.aneo.io.RoverGrammarLexer;
import org.aneo.io.RoverGrammarParser;
import org.aneo.rover.io.RoverRunner;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStreams;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.*;

public final class RoverCommandApplication {
    /**
     *
     */
    public static void main(@NonNull final String[] arguments) {
        for (@NonNull final String path : arguments) {
            RoverCommandApplication.execute(Paths.get(path));
        }
    }

    /**
     *
     */
    public static void execute(@NonNull final Path file) {
        try {
            @NonNull final InputStream input = Files.newInputStream(file, StandardOpenOption.READ);
            RoverCommandApplication.execute(input);
            input.close();
        } catch (@NonNull final IOException exception) {
            System.err.println(
                    "Unable to process the requested file \"" + file.toString() + "\" (" +
                    file.toAbsolutePath() + ") due to an IO exception."
            );
            System.err.println(exception);
        }
    }

    /**
     *
     */
    // @TODO Static memory to process batch of inputs.
    public static void execute(@NonNull final InputStream input) {
        try {
            @NonNull final RoverRunner runner = new RoverRunner();
            @NonNull final RoverGrammarLexer lexer = new RoverGrammarLexer(CharStreams.fromReader(new InputStreamReader(input)));
            @NonNull final RoverGrammarParser parser = new RoverGrammarParser(new BufferedTokenStream(lexer));

            parser.addParseListener(runner);
            parser.stream();

            if (runner.getError() != null) {
                System.err.println(runner.getError());
            }
        } catch (@NonNull final IOException exception) {
            System.err.println("Unable to process the requested input due to an IO exception.");
            System.err.println(exception);
        }
    }
}
