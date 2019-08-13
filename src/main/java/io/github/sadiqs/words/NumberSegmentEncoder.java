package io.github.sadiqs.words;

/*
 * Converts natural numbers, from 1 to 999 (inclusive)
 */
public class NumberSegmentEncoder {

    String[] primary = {"", "one", "two", "three", "four", "five", "six",
            "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen",
            "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

    String[] tens = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

    public String convert(int number) {
        if (number < 1 || number > 999) {
            throw new IllegalArgumentException("Converts only upto three digit natural numbers, but given number is " + number);
        }

        return convertThreeDigitNumber(number).trim();
    }

    private String convertSingleDigit(int number) {
        return primary[number];
    }

    private String convertTwoDigitNumber(int number) {
        if (number >= 20) {
            int significantDigit = number / 10;
            int leastDigit = number % 10;

            return tens[significantDigit] + " " + primary[leastDigit];
        } else {
            return primary[number];
        }
    }

    private String convertThreeDigitNumber(int number) {
        int significant = number / 100;
        int least = number % 100;

        if (significant > 0 && least > 0) {
            return convertSingleDigit(significant) + " hundred and " + convertTwoDigitNumber(least);
        } else if (significant > 0) {
            return convertSingleDigit(significant) + " hundred";
        } else {
            return convertTwoDigitNumber(least);
        }
    }
}
