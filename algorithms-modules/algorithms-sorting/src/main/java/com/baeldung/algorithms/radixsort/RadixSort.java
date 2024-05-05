package com.baeldung.algorithms.radixsort;

import java.util.Arrays;

public class RadixSort {

    public static void sort(int numbers[]) {
        var maximumNumber = findMaximumNumberIn(numbers);

        var numberOfDigits = calculateNumberOfDigitsIn(maximumNumber);

        var placeValue = 1;

        while (numberOfDigits-- > 0) {
            applyCountingSortOn(numbers, placeValue);
            placeValue *= 10;
        }
    }

    private static void applyCountingSortOn(int[] numbers, int placeValue) {
        var range = 10; // radix or the base

        var length = numbers.length;
        var frequency = new int[range];
        var sortedValues = new int[length];

        for (var i = 0; i < length; i++) {
            var digit = (numbers[i] / placeValue) % range;
            frequency[digit]++;
        }

        for (var i = 1; i < range; i++) {
            frequency[i] += frequency[i - 1];
        }

        for (var i = length - 1; i >= 0; i--) {
            var digit = (numbers[i] / placeValue) % range;
            sortedValues[frequency[digit] - 1] = numbers[i];
            frequency[digit]--;
        }

        System.arraycopy(sortedValues, 0, numbers, 0, length);
    }

    private static int calculateNumberOfDigitsIn(int number) {
        return (int) Math.log10(number) + 1; // valid only if number > 0
    }

    private static int findMaximumNumberIn(int[] arr) {
        return Arrays.stream(arr).max().getAsInt();
    }

}
