package com.perapoch.aoc.runner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FloatSupportTest {

    public static final class SingleFloat {

        @Input(source = "float.txt")
        public void run(Float input) {
            assertThat(input).isEqualTo(20.67f);
        }
    }

    public static final class SinglePrimitiveFloat {

        @Input(source = "float.txt")
        public void run(float input) {
            assertThat(input).isEqualTo(20.67f);
        }
    }

    public static final class Multiline {

        @Input(source = "multiline_floats.txt")
        public void run(List<Float> input) {
            assertThat(input).hasSize(100);
        }
    }

    public static final class Delimited {

        @Input(source = "delimited_floats.txt", splitBy = ",")
        public void run(List<Float> input) {
            assertThat(input).hasSize(100);
        }
    }

    public static final class MultilineDelimited {

        @Input(source = "multiline_delimited_floats.txt", splitBy = ", ")
        public void run(List<List<Float>> input) {
            assertThat(input).hasSize(50);
            input.forEach(pairOfInts -> assertThat(pairOfInts).hasSize(2));
        }
    }

    @Test
    @DisplayName("Should parse a single float input as Float")
    void singleFloatInput() {
        ChallengeRunner.run(SingleFloat.class);
    }

    @Test
    @DisplayName("Should parse a single float input as float")
    void singlePrimitiveFloatInput() {
        ChallengeRunner.run(SinglePrimitiveFloat.class);
    }

    @Test
    @DisplayName("Should parse a line-separated list of floats")
    void multilineTest() {
        ChallengeRunner.run(Multiline.class);
    }

    @Test
    @DisplayName("Should parse a comma-separated list of floats")
    void delimitedTest() {
        ChallengeRunner.run(Delimited.class);
    }

    @Test
    @DisplayName("Should parse a line-separated list of comma-separated list of floats")
    void multilineDelimitedTest() {
        ChallengeRunner.run(MultilineDelimited.class);
    }
}
