package com.perapoch.aoc.runner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegerSupportTest {

    public static final class SingleInteger {

        @Input(source = "int.txt")
        public void run(Integer input) {
            assertThat(input).isEqualTo(456);
        }
    }

    public static final class SingleInt {

        @Input(source = "int.txt")
        public void run(int input) {
            assertThat(input).isEqualTo(456);
        }
    }

    public static final class Multiline {

        @Input(source = "multiline_ints.txt")
        public void run(List<Integer> input) {
            assertThat(input).hasSize(100);
        }
    }

    public static final class Delimited {

        @Input(source = "delimited_ints.txt", splitBy = ",")
        public void run(List<Integer> input) {
            assertThat(input).hasSize(424);
        }
    }

    public static final class MultilineDelimited {

        @Input(source = "multiline_delimited_ints.txt", splitBy = ", ")
        public void run(List<List<Integer>> input) {
            assertThat(input).hasSize(50);
            input.forEach(pairOfInts -> assertThat(pairOfInts).hasSize(2));
        }
    }

    @Test
    @DisplayName("Should parse a single integer input as Integer")
    void singleIntegerInput() {
        ChallengeRunner.run(SingleInteger.class);
    }

    @Test
    @DisplayName("Should parse a single integer input as int")
    void singleIntInput() {
        ChallengeRunner.run(SingleInt.class);
    }

    @Test
    @DisplayName("Should parse a line-separated list of integers")
    void multilineTest() {
        ChallengeRunner.run(Multiline.class);
    }

    @Test
    @DisplayName("Should parse a comma-separated list of integers")
    void delimitedTest() {
        ChallengeRunner.run(Delimited.class);
    }

    @Test
    @DisplayName("Should parse a line-separated list of comma-separated list of integers")
    void multilineDelimitedTest() {
        ChallengeRunner.run(MultilineDelimited.class);
    }

}
