package com.perapoch.aoc.runner;

import com.perapoch.aoc.runner.parser.*;

public class ChallengeRunner {

    private static final ParserRegistry parserRegistry = new ParserRegistry();

    static {
        register(Integer.class, int.class, new IntegerParser());
        register(Long.class, long.class, new LongParser());
        register(Character.class, char.class, new CharacterParser());
        register(String.class, new StringParser());
    }

    private static <T> void register(Class<T> klass, AbstractInputParser<T> parser) {
        register(klass, null, parser);
    }

    private static <T> void register(Class<T> klass, Class<?> primitiveType, AbstractInputParser<T> parser) {
        String typeName = klass.getTypeName();
        parserRegistry.register(typeName, parser);
        if (primitiveType != null) {
            parserRegistry.register(primitiveType.getSimpleName(), parser);
        }
        DefaultInputListParser<T> listParser = new DefaultInputListParser<>(parser);
        parserRegistry.register(String.format("java.util.List<%s>", typeName), listParser);
        parserRegistry.register(String.format("java.util.List<java.util.List<%s>>", typeName),
                                new ListOfListsParser<>(listParser));
    }

    public static void run(Class<?> klass) {
        InputInjector inputInjector = new InputInjector(parserRegistry);
        inputInjector.inject(klass);
    }


}
