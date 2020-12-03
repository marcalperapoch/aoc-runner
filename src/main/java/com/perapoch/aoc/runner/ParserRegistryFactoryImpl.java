package com.perapoch.aoc.runner;

import com.perapoch.aoc.runner.parser.*;

public class ParserRegistryFactoryImpl implements ParserRegistryFactory {

    private final CustomParserScanner parserScanner;

    ParserRegistryFactoryImpl(final CustomParserScanner parserScanner) {
        this.parserScanner = parserScanner;
    }

    @Override
    public ParserRegistry createFrom(final Object target) {
        // scan for custom annotation and register all the found methods
        ParserRegistry parserRegistry = new ParserRegistry();
        parserRegistry.register(ParserDescriptor.of(Integer.class, int.class, new IntegerParser()));
        parserRegistry.register(ParserDescriptor.of(Long.class, long.class, new LongParser()));
        parserRegistry.register(ParserDescriptor.of(Character.class, char.class, new CharacterParser()));
        parserRegistry.register(ParserDescriptor.of(Float.class, float.class, new FloatParser()));
        parserRegistry.register(ParserDescriptor.of(Double.class, double.class, new DoubleParser()));
        parserRegistry.register(ParserDescriptor.of(String.class, new StringParser()));
        parserScanner.findCustomParsers(target).forEach(parserRegistry::register);
        return parserRegistry;
    }
}
