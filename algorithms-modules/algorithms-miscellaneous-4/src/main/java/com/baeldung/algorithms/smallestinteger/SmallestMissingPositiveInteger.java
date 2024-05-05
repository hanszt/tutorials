package com.baeldung.algorithms.smallestinteger;

import java.util.Arrays;

public class SmallestMissingPositiveInteger {
    public static int searchInSortedArray(int[] input) {
        for (var i = 0; i < input.length; i++) {
            if (i != input[i]) {
                return i;
            }
        }

        return input.length;
    }

    public static int searchInUnsortedArraySortingFirst(int[] input) {
        Arrays.sort(input);
        return searchInSortedArray(input);
    }

    public static int searchInUnsortedArrayBooleanArray(int[] input) {
        var flags = new boolean[input.length];
        for (var number : input) {
            if (number < flags.length) {
                flags[number] = true;
            }
        }

        for (var i = 0; i < flags.length; i++) {
            if (!flags[i]) {
                return i;
            }
        }

        return flags.length;
    }
}
