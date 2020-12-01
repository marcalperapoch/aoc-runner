package com.perapoch.aoc.runner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DoubleSupportTest {

    public static final class SingleDouble {

        @Input(source = "double.txt")
        public void run(Double input) {
            assertThat(input).isEqualTo(Float.MAX_VALUE + 100);
        }
    }

    public static final class SinglePrimitiveDouble {

        @Input(source = "double.txt")
        public void run(double input) {
            assertThat(input).isEqualTo(Float.MAX_VALUE + 100);
        }
    }

    public static final class Multiline {

        @Input(source = "multiline_doubles.txt")
        public void run(List<Double> input) {
            assertThat(input).hasSize(100);
        }
    }

    public static final class Delimited {

        @Input(source = "delimited_doubles.txt", splitBy = ",")
        public void run(List<Double> input) {
            assertThat(input).hasSize(100);
        }
    }

    public static final class MultilineDelimited {

        @Input(source = "multiline_delimited_doubles.txt", splitBy = ", ")
        public void run(List<List<Double>> input) {
            assertThat(input).hasSize(50);
            input.forEach(pairOfInts -> assertThat(pairOfInts).hasSize(2));
        }
    }

    @Test
    @DisplayName("Should parse a single double input as Double")
    void singleDoubleInput() {
        ChallengeRunner.run(SingleDouble.class);
    }

    @Test
    @DisplayName("Should parse a single double input as double")
    void singlePrimitiveDoubleInput() {
        ChallengeRunner.run(SinglePrimitiveDouble.class);
    }

    @Test
    @DisplayName("Should parse a line-separated list of doubles")
    void multilineTest() {
        ChallengeRunner.run(Multiline.class);
    }

    @Test
    @DisplayName("Should parse a comma-separated list of doubles")
    void delimitedTest() {
        ChallengeRunner.run(Delimited.class);
    }

    @Test
    @DisplayName("Should parse a line-separated list of comma-separated list of doubles")
    void multilineDelimitedTest() {
        ChallengeRunner.run(MultilineDelimited.class);
    }
}
