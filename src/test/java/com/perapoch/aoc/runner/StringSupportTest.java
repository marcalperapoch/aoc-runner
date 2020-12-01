package com.perapoch.aoc.runner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StringSupportTest {

    public static final class Single {

        @Input(source = "string.txt")
        public void run(String input) {
            assertThat(input).isEqualTo("Hello world");
        }
    }

    public static final class Multiline {

        @Input(source = "multiline_strings.txt")
        public void run(List<String> input) {
            assertThat(input).hasSize(250);
        }
    }

    public static final class Delimited {

        @Input(source = "delimited_strings.txt", splitBy = ",")
        public void run(List<String> input) {
            assertThat(input).hasSize(4);
        }
    }

    public static final class MultilineDelimited {

        @Input(source = "multiline_delimited_strings.txt", splitBy = "=")
        public void run(List<List<String>> input) {
            assertThat(input).hasSize(4);
            input.forEach(pairOfInts -> assertThat(pairOfInts).hasSize(2));
        }
    }

    @Test
    @DisplayName("Should parse a single String input")
    void singleInput() {
        ChallengeRunner.run(Single.class);
    }

    @Test
    @DisplayName("Should parse a line-separated list of Strings")
    void multilineTest() {
        ChallengeRunner.run(Multiline.class);
    }

    @Test
    @DisplayName("Should parse a comma-separated list of Strings")
    void delimitedTest() {
        ChallengeRunner.run(Delimited.class);
    }

    @Test
    @DisplayName("Should parse a line-separated list of =-separated Strings")
    void multilineDelimitedTest() {
        ChallengeRunner.run(MultilineDelimited.class);
    }

}
