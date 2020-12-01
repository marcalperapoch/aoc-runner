package com.perapoch.aoc.runner;

import java.io.IOException;
import java.util.stream.Stream;

public interface InputProvider {

    Stream<String> get(Class<?> klass, String sourceName) throws IOException;
}
