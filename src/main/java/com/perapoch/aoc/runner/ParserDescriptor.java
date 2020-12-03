package com.perapoch.aoc.runner;

import com.perapoch.aoc.runner.parser.AbstractInputParser;

class ParserDescriptor<T> {

    final Class<T> klass;
    final Class<?> primitiveType;
    final AbstractInputParser<T> parser;

    public static <T> ParserDescriptor<T> of(final Class<T> klass, final AbstractInputParser<T> parser) {
        return of(klass, null, parser);
    }

    public static <T> ParserDescriptor<T> of(final Class<T> klass,
                                             final Class<?> primitiveType,
                                             final AbstractInputParser<T> parser) {
        return new ParserDescriptor<>(klass, primitiveType, parser);
    }

    private ParserDescriptor(final Class<T> klass,
                             final Class<?> primitiveType,
                             final AbstractInputParser<T> parser) {
        this.klass = klass;
        this.primitiveType = primitiveType;
        this.parser = parser;
    }

    public Class<T> getKlass() {
        return klass;
    }

    public Class<?> getPrimitiveType() {
        return primitiveType;
    }

    public boolean hasPrimitiveType() {
        return primitiveType != null;
    }

    public AbstractInputParser<T> getParser() {
        return parser;
    }
}
