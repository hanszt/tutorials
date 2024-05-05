package com.baeldung.algorithms.mergesort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class MergeSortUnitTest {

    @Test
    void positiveTest() {
        var actual = new int[]{5, 1, 6, 2, 3, 4};
        var expected = new int[]{1, 2, 3, 4, 5, 6};
        MergeSort.mergeSort(actual, actual.length);
        assertArrayEquals(expected, actual);
    }

}
