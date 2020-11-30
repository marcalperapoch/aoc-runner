package com.perapoch.aoc.runner.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class PrimitiveParser<T> implements InputParser<T> {

    @Override
    public List<T> parse(final Stream<String> input) {
        return input.map(this::parse)
                    .collect(Collectors.toList());
    }

    @Override
    public List<T> parse(final Stream<String> input, final String splitBy) {
        return input.map(line -> line.split(splitBy))
                    .flatMap(Arrays::stream)
                    .map(this::parse)
                    .collect(Collectors.toList());
    }

    public abstract T parse(final String input);

}
