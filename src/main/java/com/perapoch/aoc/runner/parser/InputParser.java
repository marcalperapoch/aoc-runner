package com.perapoch.aoc.runner.parser;

import java.util.List;
import java.util.stream.Stream;

public interface InputParser<T> {

    List<T> parse(Stream<String> input);

    List<T> parse(Stream<String> input, String splitBy);
}
