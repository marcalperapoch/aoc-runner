package com.perapoch.aoc.runner.parser;

import com.perapoch.aoc.runner.InputParser;

import java.util.stream.Stream;

public abstract class AbstractInputParser<T> implements InputParser<T> {

    @Override
    public T parse(final Stream<String> input) {
        return input.map(this::parse)
                    .findFirst()
                    .orElse(null);
    }

    public abstract T parse(final String input);

}
