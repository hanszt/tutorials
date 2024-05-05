package com.baeldung.algorithms.insertionsort;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class InsertionSortUnitTest {

    @Test
    void givenUnsortedArray_whenInsertionSortImperative_thenSortedAsc() {
        var input = new int[]{6, 2, 3, 4, 5, 1};
        InsertionSort.insertionSortImperative(input);
        var expected = new int[]{1, 2, 3, 4, 5, 6};
        assertArrayEquals(expected, input, "the two arrays are not equal");
    }

    @Test
    void givenUnsortedArray_whenInsertionSortRecursive_thenSortedAsc() {
        var input = new int[]{6, 4, 5, 2, 3, 1};
        InsertionSort.insertionSortRecursive(input);
        var expected = new int[]{1, 2, 3, 4, 5, 6};
        assertArrayEquals( expected, input, "the two arrays are not equal");
    }
}
