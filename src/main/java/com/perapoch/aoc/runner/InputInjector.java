package com.perapoch.aoc.runner;

import com.perapoch.aoc.runner.parser.InputParser;
import com.perapoch.aoc.runner.parser.ParserRegistry;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputInjector {

    private final ParserRegistry parserRegistry;

    public InputInjector(final ParserRegistry parserRegistry) {
        this.parserRegistry = parserRegistry;
    }

    public void inject(final Class<?> klass) {
        Method method = getInputMethod(klass);

        Type[] genericParameterTypes = method.getGenericParameterTypes();
        if (genericParameterTypes.length < 1 || !genericParameterTypes[0].getTypeName().startsWith("java.util.List")) {
            throw new IllegalArgumentException("Missing mandatory List argument");
        }
        ParameterizedType genericParameterType = (ParameterizedType) genericParameterTypes[0];
        Type listType = genericParameterType.getActualTypeArguments()[0];

        try {
            String typeName = listType.getTypeName();
            System.out.println("About to inject a list of type: " + listType);

            Object challenge = klass.getConstructor().newInstance();

            if (!parserRegistry.isSupported(typeName)) {
                throw new IllegalStateException("Unsupported type " + typeName);
            }

            Input input = method.getAnnotation(Input.class);
            String source = input.source();
            String splitBy = input.splitBy();

            String prefix = createPrefix(klass);
            List<?> challengeInput = parseInputFromFile(typeName, prefix, source, splitBy);
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

    private String createPrefix(final Class<?> klass) {
        return "src/main/resources/" + klass.getPackageName().replace(".", "/") + "/";
    }

    private List<?> parseInputFromFile(final String typeName,
                                       final String prefix,
                                       final String source,
                                       final String splitBy) {
        try (Stream<String> stream = Files.lines(Paths.get(prefix + source))) {
            InputParser<?> parser = parserRegistry.getParser(typeName);
            return splitBy.isEmpty() ? parser.parse(stream) : parser.parse(stream, splitBy);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to read " + source + " from " + prefix);
        }
    }
}
