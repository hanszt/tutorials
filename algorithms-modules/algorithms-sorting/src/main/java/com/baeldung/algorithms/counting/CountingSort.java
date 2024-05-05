package com.baeldung.algorithms.counting;

import java.util.Arrays;
import java.util.stream.IntStream;

public class CountingSort {

    public static int[] sort(int[] input, int k) {
        verifyPreconditions(input, k);
        if (input.length == 0) {
            return input;
        }

        var c = countElements(input, k);
        var sorted = new int[input.length];
        for (var i = input.length - 1; i >= 0; i--) {
            var current = input[i];
            sorted[c[current] - 1] = current;
            c[current] -= 1;
        }

        return sorted;
    }

    static int[] countElements(int[] input, int k) {
        var c = new int[k + 1];
        Arrays.fill(c, 0);
        for (var i : input) {
            c[i] += 1;
        }

        for (var i = 1; i < c.length; i++) {
            c[i] += c[i - 1];
        }
        return c;
    }

    private static void verifyPreconditions(int[] input, int k) {
        if (input == null) {
            throw new IllegalArgumentException("Input is required");
        }

        var min = IntStream.of(input).min().getAsInt();
        var max = IntStream.of(input).max().getAsInt();

        if (min < 0 || max > k) {
            throw new IllegalArgumentException("The input numbers should be between zero and " + k);
        }
    }
}
