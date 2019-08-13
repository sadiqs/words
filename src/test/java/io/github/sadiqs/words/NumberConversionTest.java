package io.github.sadiqs.words;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NumberConversionTest {

    @Mock
    private NumberSegmentEncoder segmentEncoder;

    @InjectMocks
    private NumberConverter converter;

    @Before
    public void setUp() {
        when(segmentEncoder.convert(anyInt())).thenReturn("dummy");
    }

    @Test
    public void shouldConvertValueLessThanThousandToNoDenomination() {
        assertThat(converter.convert(123)).isEqualTo("dummy");
    }

    @Test
    public void shouldConvertSegmentsAccordingToDenomination() {
        assertThat(converter.convert(123_123)).isEqualTo("dummy thousand dummy");
        assertThat(converter.convert(123_123_123)).isEqualTo("dummy million dummy thousand dummy");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForNegativeNumbers() {
        converter.convert(-23);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForLargeNumbers() {
        converter.convert(1_000_000_000);
    }

}
