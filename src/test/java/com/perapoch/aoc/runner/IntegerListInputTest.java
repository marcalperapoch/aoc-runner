package com.perapoch.aoc.runner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegerListInputTest {

    public static final class LineSeparatedIntegers {

        @Input(source = "ints.txt")
        public void run(List<Integer> input) {
            assertThat(input).hasSize(100);
        }
    }

    public static final class CommaSeparatedIntegers {

        @Input(source = "ints_list.txt", splitBy = ",")
        public void run(List<Integer> input) {
            assertThat(input).hasSize(424);
        }
    }

    public static final class LineSeparatedCommaSeparatedIntegers {

        @Input(source = "ints_list_list.txt", splitBy = ", ")
        public void run(List<List<Integer>> input) {
            assertThat(input).hasSize(50);
            input.forEach(pairOfInts -> assertThat(pairOfInts).hasSize(2));
        }
    }

    @Test
    @DisplayName("Should parse a line-separated list of integers")
    void lineSeparatedIntegerListTest() {
        ChallengeRunner.run(LineSeparatedIntegers.class);
    }

    @Test
    @DisplayName("Should parse a comma-separated list of integers")
    void commaSeparatedIntegerListTest() {
        ChallengeRunner.run(CommaSeparatedIntegers.class);
    }

    @Test
    @DisplayName("Should parse a line-separated list of comma-separated list of integers")
    void lineSeparatedCommaSeparatedIntegerListTest() {
        ChallengeRunner.run(LineSeparatedCommaSeparatedIntegers.class);
    }

}
