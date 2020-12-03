package com.perapoch.aoc.runner.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListOfListsParser<T> implements InputListParser<List<T>> {

    private final DefaultInputListParser<T> abstractListParser;

    public ListOfListsParser(final DefaultInputListParser<T> abstractListParser) {
        this.abstractListParser = abstractListParser;
    }

    @Override
    public List<List<T>> parse(final Stream<String> input) {
        throw new UnsupportedOperationException("Should not be called");
    }

    @Override
    public List<List<T>> parse(final Stream<String> input, final String splitBy) {
        return input.map(line -> line.split(splitBy))
                    .map(elements -> Arrays.stream(elements).map(abstractListParser::parse).collect(Collectors.toList()))
                    .collect(Collectors.toList());
    }
}
