package com.perapoch.aoc.runner.parser;

import java.util.function.Function;

public class CustomMethodParser<T> extends AbstractInputParser<T> {

    private final Function<String, T> parsingMethod;

    public CustomMethodParser(final Function<String, T> parsingMethod) {
        this.parsingMethod = parsingMethod;
    }

    @Override
    public T parse(final String input) {
        return parsingMethod.apply(input);
    }
}
