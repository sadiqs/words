package io.github.sadiqs.words;

import java.util.ArrayList;
import java.util.List;

public class NumberConverter {

    private static final int MAX = 999_999_999;

    String[] denominations = {"", "thousand", "million"};

    private NumberSegmentEncoder partConverter;

    public NumberConverter(NumberSegmentEncoder partConverter) {
        this.partConverter = partConverter;
    }

    public String convert(int number) {
        if (number < 0 || number > MAX) {
            throw new IllegalArgumentException("The number " + number + " is out of range");
        }

        if (number == 0) {
            return "zero";
        }

        List<Integer> parts = splitToThreeDigitNumbers(number);
        int position = 0;
        String inWords = "";

        for (Integer part : parts) {
            String threeDigitNumber = partConverter.convert(part);
            inWords = denominate(threeDigitNumber, position) + " " + inWords;
            position++;
        }

        return inWords.trim();
    }

    private String denominate(String number, int position) {
        return number + " " + denominations[position];
    }

    private List<Integer> splitToThreeDigitNumbers(int number) {
        List<Integer> parts = new ArrayList<>();
        while (number > 0) {
            parts.add(number % 1000);
            number /= 1000;
        }
        return parts;
    }

}
