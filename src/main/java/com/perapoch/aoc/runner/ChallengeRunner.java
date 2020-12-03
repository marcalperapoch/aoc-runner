package com.perapoch.aoc.runner;

public class ChallengeRunner {

    private final InputProvider inputProvider;
    private final ParserRegistryFactory parserRegistryFactory;

    ChallengeRunner(final InputProvider inputProvider,
                    final ParserRegistryFactory parserRegistryFactory) {
        this.inputProvider = inputProvider;
        this.parserRegistryFactory = parserRegistryFactory;
    }


    public void runChallenge(Class<?> klass) {
        try {
            Object challenge = klass.getConstructor().newInstance();
            ParserRegistry parserRegistry = parserRegistryFactory.createFrom(challenge);
            InputInjector inputInjector = new InputInjector(parserRegistry, inputProvider);
            inputInjector.injectInputAndRun(challenge);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to instantiate a new object of class " + klass, e);
        }
    }

    public static void run(Class<?> klass) {
        ChallengeRunner runner = new ChallengeRunner(new FileInputProvider(),
                                                     new ParserRegistryFactoryImpl(new AnnotatedCustomParserScanner()));
        runner.runChallenge(klass);
    }


}
