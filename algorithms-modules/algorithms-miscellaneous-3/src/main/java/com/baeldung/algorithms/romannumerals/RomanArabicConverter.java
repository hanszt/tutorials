package com.baeldung.algorithms.romannumerals;

import java.util.List;

class RomanArabicConverter {

    public static int romanToArabic(String input) {
        var romanNumeral = input.toUpperCase();
        var result = 0;

        var romanNumerals = RomanNumeral.getReverseSortedValues();

        var i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            var symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }
        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        }

        return result;
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (0,4000]");
        }

        var romanNumerals = RomanNumeral.getReverseSortedValues();

        var i = 0;
        var sb = new StringBuilder();

        while (number > 0 && i < romanNumerals.size()) {
            var currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }
        return sb.toString();
    }
}
