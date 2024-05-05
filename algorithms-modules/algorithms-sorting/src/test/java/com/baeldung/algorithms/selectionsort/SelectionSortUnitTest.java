package com.baeldung.algorithms.selectionsort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class SelectionSortUnitTest {

    @Test
    void givenUnsortedArray_whenSelectionSort_SortAscending_thenSortedAsc() {
        var input = new int[]{5, 4, 1, 6, 2};
        SelectionSort.sortAscending(input);
        var expected = new int[]{1, 2, 4, 5, 6};
        assertArrayEquals(expected, input, "the two arrays are not equal");
    }
    
    @Test
    void givenUnsortedArray_whenSelectionSort_SortDescending_thenSortedDesc() {
        var input = new int[]{5, 4, 1, 6, 2};
        SelectionSort.sortDescending(input);
        var expected = new int[]{6, 5, 4, 2, 1};
        assertArrayEquals(expected, input, "the two arrays are not equal");
    }
}
