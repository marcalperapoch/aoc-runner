package com.perapoch.aoc.runner.parser;

public class DoubleParser extends AbstractInputParser<Double> {

    @Override
    public Double parse(final String input) {
        return Double.parseDouble(input);
    }
}
