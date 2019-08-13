package io.github.sadiqs.words;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class NumberSegmentEncoderTest {

    @Parameters(name = "{index}: {0} should be converted to \"{1}\"")
    public static Iterable<Object[]> expectations() {
        return Arrays.asList(new Object[][]{
                {1, "one"},
                {9, "nine"},
                {11, "eleven"},
                {20, "twenty"},
                {21, "twenty one"},
                {100, "one hundred"},
                {105, "one hundred and five"},
                {-1, IllegalArgumentException.class},
                {1000, IllegalArgumentException.class}
        });
    }

    @Parameter(0)
    public int number;

    @Parameter(1)
    public Object expected;

    NumberSegmentEncoder segmentEncoder = new NumberSegmentEncoder();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldConvertToExpectedValue() {
        if (expected instanceof Class<?>) {
            expectedException.expect((Class<? extends Exception>) expected);
        }
        assertThat(segmentEncoder.convert(number)).isEqualTo(expected);
    }

}
