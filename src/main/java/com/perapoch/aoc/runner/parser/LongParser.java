package com.perapoch.aoc.runner.parser;

public class LongParser extends PrimitiveParser<Long> {

    @Override
    public Long parse(final String input) {
        return Long.parseLong(input);
    }
}
