package io.github.sadiqs.words;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

@RunWith(Parameterized.class)
public class NumberConversionIntegrationTest {

    ExpectedException a = ExpectedException.none();

    @Parameterized.Parameters(name = "{index}: {0} should be converted to \"{1}\"")
    public static Iterable<Object[]> expectations() {
        return Arrays.asList(new Object[][]{
                {0, "zero"},
                {1, "one"},
                {9, "nine"},
                {20, "twenty"},
                {21, "twenty one"},
                {99, "ninety nine"},
                {100, "one hundred"},
                {105, "one hundred and five"},
                {56_945_781, "fifty six million nine hundred and forty five thousand seven hundred and eighty one"},
                {999_999_999, "nine hundred and ninety nine million nine hundred and ninety nine thousand nine hundred and ninety nine"}
        });
    }

    private final int number;

    private final String expected;

    private final NumberConverter converter = new NumberConverter(new NumberSegmentEncoder());

    public NumberConversionIntegrationTest(int number, String expected) {
        this.number = number;
        this.expected = expected;
    }

    @Test
    public void shouldConvertToExpectedValue() {
        assertThat(converter.convert(number)).isEqualTo(expected);
    }
}
