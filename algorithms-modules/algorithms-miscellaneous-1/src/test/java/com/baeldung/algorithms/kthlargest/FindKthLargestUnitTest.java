package com.baeldung.algorithms.kthlargest;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class FindKthLargestUnitTest {


    private final Random random = new Random(0);
    private final FindKthLargest findKthLargest = new FindKthLargest(random);
    private final int[] arr = { 3, 7, 1, 2, 8, 10, 4, 5, 6, 9 };

    @Test
    void givenIntArray_whenFindKthLargestBySorting_thenGetResult() {
        final var k = 3;
        assertThat(findKthLargest.findKthLargestBySorting(arr, k)).isEqualTo(8);
    }

    @Test
    void givenIntArray_whenFindKthLargestBySortingDesc_thenGetResult() {
        final var k = 3;
        assertThat(findKthLargest.findKthLargestBySortingDesc(arr, k)).isEqualTo(8);
    }

    @Test
    void givenIntArray_whenFindKthLargestByQuickSelect_thenGetResult() {
        final var k = 3;
        final var kthLargest = arr.length - k;
        assertThat(findKthLargest.findKthElementByQuickSelect(arr, 0, arr.length - 1, kthLargest)).isEqualTo(8);
    }

    @Test
    void givenIntArray_whenFindKthElementByQuickSelectIterative_thenGetResult() {
        final var k = 3;
        final var kthLargest = arr.length - k;
        assertThat(findKthLargest.findKthElementByQuickSelectWithIterativePartition(arr, 0, arr.length - 1, kthLargest)).isEqualTo(8);
    }

    @Test
    void givenIntArray_whenFindKthSmallestByQuickSelect_thenGetResult() {
        final var k = 3;
        assertThat(findKthLargest.findKthElementByQuickSelect(arr, 0, arr.length - 1, k - 1)).isEqualTo(3);
    }

    @Test
    void givenIntArray_whenFindKthLargestByRandomizedQuickSelect_thenGetResult() {
        final var k = 3;
        final var kthLargest = arr.length - k;
        assertThat(findKthLargest.findKthElementByRandomizedQuickSelect(arr, 0, arr.length - 1, kthLargest)).isEqualTo(8);
    }

}
