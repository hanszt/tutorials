package com.baeldung.algorithms.counting;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class CountingSortUnitTest {

    @Test
    void countElements_GivenAnArray_ShouldCalculateTheFrequencyArrayAsExpected() {
        var k = 5;
        var input = new int[]{4, 3, 2, 5, 4, 3, 5, 1, 0, 2, 5};

        var c = CountingSort.countElements(input, k);
        var expected = new int[]{1, 2, 4, 6, 8, 11};
        assertArrayEquals(expected, c);
    }

    @Test
    void sort_GivenAnArray_ShouldSortTheInputAsExpected() {
        var k = 5;
        var input = new int[]{4, 3, 2, 5, 4, 3, 5, 1, 0, 2, 5};

        var sorted = CountingSort.sort(input, k);

        // Our sorting algorithm and Java's should return the same result
        Arrays.sort(input);
        assertArrayEquals(input, sorted);
    }
}