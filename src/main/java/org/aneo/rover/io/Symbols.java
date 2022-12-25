package org.aneo.rover.io;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Iterator;

public final class Symbols {
    /**
     *
     */
    public @NonNull Iterator<@NonNull Symbol> fromString(@NonNull final String content) {
        return Collections.emptyIterator();
    }

    /**
     *
     */
    public @NonNull Iterator<@NonNull Symbol> fromFile(@NonNull final Path path) throws IOException {
        @NonNull final BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);


        return Collections.emptyIterator();
    }
}
