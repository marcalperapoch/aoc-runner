package com.perapoch.aoc.runner.parser;

public class LongParser extends AbstractInputParser<Long> {

    @Override
    public Long parse(final String input) {
        return Long.parseLong(input);
    }
}
