package com.perapoch.aoc.runner;

import com.perapoch.aoc.runner.parser.*;

public class ChallengeRunner {

    private static final ParserRegistry parserRegistry = new ParserRegistry();

    static {
        parserRegistry.register("java.lang.Integer", new IntegerParser());
        parserRegistry.register("java.lang.Long", new LongParser());
        parserRegistry.register("java.lang.Character", new CharacterParser());
        parserRegistry.register("java.lang.String", new StringParser());
        parserRegistry.register("java.util.List<java.lang.Integer>", new ListParser<>(new IntegerParser()));
        parserRegistry.register("java.util.List<java.lang.Long>", new ListParser<>(new LongParser()));
        parserRegistry.register("java.util.List<java.lang.Character>", new ListParser<>(new CharacterParser()));
        parserRegistry.register("java.util.List<java.lang.String>", new ListParser<>(new StringParser()));
    }

    public static void run(Class<?> klass) {
        InputInjector inputInjector = new InputInjector(parserRegistry);
        inputInjector.inject(klass);
    }


}
