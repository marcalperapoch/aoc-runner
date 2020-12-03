package com.perapoch.aoc.runner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CharacterSupportTest {

    public static final class SingleCharacter {

        @Input(source = "char.txt")
        public void run(Character input) {
            assertThat(input).isEqualTo('a');
        }
    }

    public static final class SingleChar {

        @Input(source = "char.txt")
        public void run(char input) {
            assertThat(input).isEqualTo('a');
        }
    }

    public static final class Multiline {

        @Input(source = "multiline_chars.txt")
        public void run(List<Character> input) {
            assertThat(input).hasSize(100);
        }
    }

    public static final class Delimited {

        @Input(source = "delimited_chars.txt", splitBy = ",")
        public void run(List<Character> input) {
            assertThat(input).hasSize(100);
        }
    }

    public static final class MultilineDelimited {

        @Input(source = "multiline_delimited_chars.txt", splitBy = "#")
        public void run(List<List<Character>> input) {
            assertThat(input).hasSize(50);
            input.forEach(pairOfInts -> assertThat(pairOfInts).hasSize(3));
        }
    }

    @Test
    @DisplayName("Should parse a single char input as Character")
    void singleCharacterInput() {
        ChallengeRunner.run(SingleCharacter.class);
    }

    @Test
    @DisplayName("Should parse a single char input as char")
    void singleCharInput() {
        ChallengeRunner.run(SingleChar.class);
    }

    @Test
    @DisplayName("Should parse a line-separated list of chars")
    void multilineTest() {
        ChallengeRunner.run(Multiline.class);
    }

    @Test
    @DisplayName("Should parse a comma-separated list of chars")
    void delimitedTest() {
        ChallengeRunner.run(Delimited.class);
    }

    @Test
    @DisplayName("Should parse a line-separated list of comma-separated list of chars")
    void multilineDelimitedTest() {
        ChallengeRunner.run(MultilineDelimited.class);
    }
}
