package com.perapoch.aoc.runner.parser;

public class CharacterParser extends AbstractInputParser<Character> {

    @Override
    public Character parse(final String input) {
        return input.charAt(0);
    }
}
