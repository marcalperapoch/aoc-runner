package com.perapoch.aoc.runner;

import java.util.List;
import java.util.stream.Stream;

public interface InputListParser<T> extends InputParser<List<T>> {

    List<T> parse(Stream<String> input, String splitBy);
}
