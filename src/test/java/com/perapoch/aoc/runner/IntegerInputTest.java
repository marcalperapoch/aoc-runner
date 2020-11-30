package com.perapoch.aoc.runner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegerInputTest {

    public static final class PrimitiveInteger {

        @Input(source = "int.txt")
        public void run(int input) {
            assertThat(input).isEqualTo(456);
        }
    }

    @Test
    @DisplayName("Should parse a primitive integer")
    void lineSeparatedIntegerListTest() {
        ChallengeRunner.run(PrimitiveInteger.class);
    }

}
