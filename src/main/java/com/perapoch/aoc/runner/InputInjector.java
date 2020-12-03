package com.perapoch.aoc.runner;

import com.perapoch.aoc.runner.parser.InputListParser;
import com.perapoch.aoc.runner.parser.InputParser;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputInjector {

    private final ParserRegistry parserRegistry;
    private final InputProvider inputProvider;

    InputInjector(final ParserRegistry parserRegistry, final InputProvider inputProvider) {
        this.parserRegistry = parserRegistry;
        this.inputProvider = inputProvider;
    }

    public void injectInputAndRun(final Object challenge) {
        Class<?> klass = challenge.getClass();
        Method method = getInputMethod(klass);

        Type[] genericParameterTypes = method.getGenericParameterTypes();
        if (genericParameterTypes.length != 1) {
            throw new IllegalStateException("Method " + method.getName() + " must have a single argument");
        }
        String typeName = genericParameterTypes[0].getTypeName();
        if (!parserRegistry.isSupported(typeName)) {
            throw new IllegalArgumentException("Unsupported type " + typeName);
        }

        try {
            System.out.println("About to inject input of type : " + typeName); // TODO: remove me

            Input input = method.getAnnotation(Input.class);
            String source = input.source();
            String splitBy = input.splitBy();

            Object challengeInput = parseInput(klass, typeName, source, splitBy);
            method.invoke(challenge, challengeInput);
        } catch (Exception e) {
            throw new IllegalStateException("Unable to call annotated method of " + klass.getName(), e);
        }
    }

    private Method getInputMethod(final Class<?> klass) {
        List<Method> methods = Arrays.stream(klass.getDeclaredMethods())
                                     .filter(m -> m.isAnnotationPresent(Input.class))
                                     .collect(Collectors.toList());
        if (methods.isEmpty()) {
            throw new IllegalArgumentException("No method found with @Input annotation");
        }
        if (methods.size() > 1) {
            throw new IllegalStateException("Only one method with the @Input annotation is supported");
        }
        return methods.get(0);
    }

    private Object parseInput(final Class<?> klass, final String typeName, final String source, final String splitBy) {
        boolean isList = typeName.startsWith("java.util.List");
        try (Stream<String> stream = inputProvider.get(klass, source)) {
            if (isList) {
                InputListParser<?> parser = parserRegistry.getListParser(typeName);
                return splitBy.isEmpty() ? parser.parse(stream) : parser.parse(stream, splitBy);
            } else {
                InputParser<?> parser = parserRegistry.getParser(typeName);
                return parser.parse(stream);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to read " + source + " for " + klass.getName());
        }
    }
}
