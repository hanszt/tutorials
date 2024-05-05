package com.baeldung.algorithms.quicksort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class QuickSortUnitTest {

    @Test
    void givenIntegerArray_whenSortedWithQuickSort_thenGetSortedArray() {
        var actual = new int[]{9, 5, 1, 0, 6, 2, 3, 4, 7, 8};
        var expected = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        QuickSort.quickSort(actual, 0, actual.length-1);
        assertArrayEquals(expected, actual);
    }

}
