package com.perapoch.aoc.runner.parser;

import com.perapoch.aoc.runner.parser.InputParser;

import java.util.HashMap;
import java.util.Map;

public class ParserRegistry {

    private final Map<String, InputParser<?>> parsers;

    public ParserRegistry() {
        this.parsers = new HashMap<>();
    }

    public void register(final String type, final InputParser<?> parser) {
        InputParser<?> alreadyExisting = parsers.putIfAbsent(type, parser);
        if (alreadyExisting != null) {
            throw new IllegalArgumentException("There's already a parser for type " + type);
        }
    }

    public boolean isSupported(final String type) {
        return parsers.containsKey(type);
    }

    public InputParser<?> getParser(final String type) {
        return parsers.get(type);
    }
}
