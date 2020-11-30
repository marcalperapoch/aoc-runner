package com.perapoch.aoc.runner;

import java.util.stream.Stream;

public interface InputParser<T> {

    T parse(Stream<String> input);
}
