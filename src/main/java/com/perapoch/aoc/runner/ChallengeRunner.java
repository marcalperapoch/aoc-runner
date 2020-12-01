package com.perapoch.aoc.runner;

import com.perapoch.aoc.runner.parser.*;

import static com.perapoch.aoc.runner.InputInjector.using;

public class ChallengeRunner {

    private final InputProvider inputProvider;
    private final ParserRegistry parserRegistry;

    ChallengeRunner(final InputProvider inputProvider) {
        this.inputProvider = inputProvider;
        this.parserRegistry = createParserRegistry();
    }

    private ParserRegistry createParserRegistry() {
        ParserRegistry parserRegistry = new ParserRegistry();
        register(parserRegistry, Integer.class, int.class, new IntegerParser());
        register(parserRegistry, Long.class, long.class, new LongParser());
        register(parserRegistry, Character.class, char.class, new CharacterParser());
        register(parserRegistry, Float.class, float.class, new FloatParser());
        register(parserRegistry, Double.class, double.class, new DoubleParser());
        register(parserRegistry, String.class, new StringParser());
        return parserRegistry;
    }


    private <T> void register(ParserRegistry parserRegistry, Class<T> klass, AbstractInputParser<T> parser) {
        register(parserRegistry, klass, null, parser);
    }

    private <T> void register(ParserRegistry parserRegistry, Class<T> klass, Class<?> primitiveType, AbstractInputParser<T> parser) {
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

    public void runChallenge(Class<?> klass) {
        using(parserRegistry, inputProvider).injectInputAndRun(klass);
    }

    public static void run(Class<?> klass) {
        ChallengeRunner runner = new ChallengeRunner(new FileInputProvider());
        runner.runChallenge(klass);
    }


}
