package com.perapoch.aoc.runner.parser;

public class FloatParser extends AbstractInputParser<Float> {

    @Override
    public Float parse(final String input) {
        return Float.parseFloat(input);
    }
}
