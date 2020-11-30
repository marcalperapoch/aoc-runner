package com.perapoch.aoc.runner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StringListInputTest {

    public static final class LineSeparatedStrings {

        @Input(source = "strings.txt")
        public void run(List<String> input) {
            assertThat(input).hasSize(250);
        }
    }

    @Test
    @DisplayName("Should parse a line-separated list of strings")
    void lineSeparatedStringsListTest() {
        ChallengeRunner.run(LineSeparatedStrings.class);
    }
}
