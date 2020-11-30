package com.perapoch.aoc.runner;

import java.util.HashMap;
import java.util.Map;

public class ParserRegistry {


    private final Map<String, InputParser<?>> parsers;
    private final Map<String, InputListParser<?>> listParsers;

    public ParserRegistry() {
        this.parsers = new HashMap<>();
        this.listParsers = new HashMap<>();
    }

    public void register(final String type, final InputParser<?> parser) {
        InputParser<?> alreadyExisting = parsers.putIfAbsent(type, parser);
        if (alreadyExisting != null) {
            throw new IllegalArgumentException("There's already a parser for type " + type);
        }
    }

    public void register(final String type, final InputListParser<?> parser) {
        InputParser<?> alreadyExisting = listParsers.putIfAbsent(type, parser);
        if (alreadyExisting != null) {
            throw new IllegalArgumentException("There's already a list parser for type " + type);
        }
    }

    public boolean isSupported(final String type) {
        return parsers.containsKey(type) || listParsers.containsKey(type);
    }

    public InputListParser<?> getListParser(final String type) {
        return listParsers.get(type);
    }

    public InputParser<?> getParser(final String type) {
        return parsers.get(type);
    }
}
