package com.perapoch.aoc.runner;

import com.perapoch.aoc.runner.parser.CustomMethodParser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AnnotatedCustomParserScanner implements CustomParserScanner {

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<ParserDescriptor> findCustomParsers(final Object target) {
        Class<?> klass = target.getClass();
        return Arrays.stream(klass.getDeclaredMethods())
                     .filter(m -> m.isAnnotationPresent(CustomParser.class))
                     .map(method -> {
                         Class returnType = method.getReturnType();
                         CustomMethodParser customMethodParser = new CustomMethodParser<>((input) -> {
                             try {
                                 return returnType.cast(method.invoke(target, input));
                             } catch (IllegalAccessException | InvocationTargetException e) {
                                 throw new IllegalStateException("Exception while calling @CustomParser method", e);
                             }
                         });
                         return ParserDescriptor.of(returnType, customMethodParser);
                     }).collect(Collectors.toList());
    }
}
