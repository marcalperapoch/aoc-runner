package com.perapoch.aoc.runner.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListParser<T> implements InputParser<List<?>> {

    private final PrimitiveParser<T> primitiveParser;

    public ListParser(final PrimitiveParser<T> primitiveParser) {
        this.primitiveParser = primitiveParser;
    }

    @Override
    public List<List<?>> parse(final Stream<String> input) {
        throw new UnsupportedOperationException("Should not be called");
    }

    @Override
    public List<List<?>> parse(final Stream<String> input, final String splitBy) {
        return input.map(line -> line.split(splitBy))
                    .map(elements -> Arrays.stream(elements).map(primitiveParser::parse).collect(Collectors.toList()))
                    .collect(Collectors.toList());
    }
}
