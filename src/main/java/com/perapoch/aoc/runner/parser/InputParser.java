package com.perapoch.aoc.runner.parser;

import java.util.stream.Stream;

public interface InputParser<T> {

    T parse(Stream<String> input);
}
