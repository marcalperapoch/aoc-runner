package com.perapoch.aoc.runner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomParserSupportTest {

    public static final class MyCustomClass {
        final String prefix;
        final int number;

        public MyCustomClass(final String prefix, final int number) {
            this.prefix = prefix;
            this.number = number;
        }
    }

    public static final class CustomSingleInput {

        @CustomParser
        public MyCustomClass parse(String line) {
            String[] parts = line.split("#");
            return new MyCustomClass(parts[0], Integer.parseInt(parts[1]));
        }

        @Input(source = "custom_object.txt")
        public void run(MyCustomClass input) {
            assertThat(input).isNotNull();
            assertThat(input.prefix).isEqualTo("prefix");
            assertThat(input.number).isEqualTo(99);
        }
    }

    public static final class Multiline {

        @CustomParser
        public MyCustomClass parse(String line) {
            String[] parts = line.split("#");
            return new MyCustomClass(parts[0], Integer.parseInt(parts[1]));
        }

        @Input(source = "multiline_custom_objects.txt")
        public void run(List<MyCustomClass> input) {
            assertThat(input).isNotNull();
            assertThat(input).hasSize(5);
        }
    }

    public static final class Delimited {

        @CustomParser
        public MyCustomClass parse(String line) {
            String[] parts = line.split("#");
            return new MyCustomClass(parts[0], Integer.parseInt(parts[1]));
        }

        @Input(source = "delimited_custom_objects.txt", splitBy = ", ")
        public void run(List<MyCustomClass> input) {
            assertThat(input).isNotNull();
            assertThat(input).hasSize(4);
        }
    }

    public static final class MultilineDelimited {

        @CustomParser
        public MyCustomClass parse(String line) {
            String[] parts = line.split("#");
            return new MyCustomClass(parts[0], Integer.parseInt(parts[1]));
        }

        @Input(source = "multiline_delimited_custom_objects.txt", splitBy = ", ")
        public void run(List<List<MyCustomClass>> input) {
            assertThat(input).isNotNull();
            assertThat(input).hasSize(5);
            input.forEach(pair -> assertThat(pair).hasSize(2));
        }
    }

    @Test
    @DisplayName("Should parse a single custom object input")
    void singleCustomObjectInput() {
        ChallengeRunner.run(CustomSingleInput.class);
    }

    @Test
    @DisplayName("Should parse a line-separated list of custom objects")
    void multilineTest() {
        ChallengeRunner.run(Multiline.class);
    }

    @Test
    @DisplayName("Should parse a comma-separated list of custom objects")
    void delimitedTest() {
        ChallengeRunner.run(Delimited.class);
    }

    @Test
    @DisplayName("Should parse a line-separated list of comma-separated list of custom objects")
    void multilineDelimitedTest() {
        ChallengeRunner.run(MultilineDelimited.class);
    }
}
