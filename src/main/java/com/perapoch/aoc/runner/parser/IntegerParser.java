package com.perapoch.aoc.runner.parser;

public class IntegerParser extends AbstractInputParser<Integer> {

    @Override
    public Integer parse(final String input) {
        return Integer.parseInt(input);
    }
}
