package com.perapoch.aoc.runner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileInputProvider implements InputProvider {

    @Override
    public Stream<String> get(final Class<?> klass, final String sourceName) throws IOException {
        String prefix = createInputPathPrefix(klass);
        return Files.lines(Paths.get(prefix + sourceName));
    }

    private String createInputPathPrefix(final Class<?> klass) {
        return "src/main/resources/" + klass.getPackageName().replace(".", "/") + "/";
    }
}
