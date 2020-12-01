package com.perapoch.aoc.runner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LongSupportTest {

    public static final class SingleLong {

        @Input(source = "long.txt")
        public void run(Long input) {
            assertThat(input).isEqualTo(Long.MAX_VALUE);
        }
    }

    public static final class SinglePrimitiveLong {

        @Input(source = "long.txt")
        public void run(long input) {
            assertThat(input).isEqualTo(Long.MAX_VALUE);
        }
    }

    public static final class Multiline {

        @Input(source = "multiline_longs.txt")
        public void run(List<Long> input) {
            assertThat(input).hasSize(100);
        }
    }

    public static final class Delimited {

        @Input(source = "delimited_longs.txt", splitBy = ", ")
        public void run(List<Long> input) {
            assertThat(input).hasSize(100);
        }
    }

    public static final class MultilineDelimited {

        @Input(source = "multiline_delimited_longs.txt", splitBy = ";")
        public void run(List<List<Long>> input) {
            assertThat(input).hasSize(100);
            input.forEach(pairOfInts -> assertThat(pairOfInts).hasSize(3));
        }
    }

    @Test
    @DisplayName("Should parse a single long input as Long")
    void singleLongInput() {
        ChallengeRunner.run(SingleLong.class);
    }

    @Test
    @DisplayName("Should parse a single long input as long")
    void singlePrimitiveLongInput() {
        ChallengeRunner.run(SinglePrimitiveLong.class);
    }

    @Test
    @DisplayName("Should parse a line-separated list of longs")
    void multilineTest() {
        ChallengeRunner.run(Multiline.class);
    }

    @Test
    @DisplayName("Should parse a comma-separated list of longs")
    void delimitedTest() {
        ChallengeRunner.run(Delimited.class);
    }

    @Test
    @DisplayName("Should parse a line-separated list of comma-separated list of longs")
    void multilineDelimitedTest() {
        ChallengeRunner.run(MultilineDelimited.class);
    }

}
