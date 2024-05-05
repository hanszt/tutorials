package com.baeldung.algorithms.maximumsubarray;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KadaneAlgorithmUnitTest {

    @Test
    void givenArrayWithNegativeNumberWhenMaximumSubarrayThenReturnsExpectedResult() {
        //given
        var arr = new int[] { -3, 1, -8, 4, -1, 2, 1, -5, 5 };
        //when
        var algorithm = new KadaneAlgorithm();
        var maxSum = algorithm.maxSubArraySum(arr);
        //then
        assertEquals(6, maxSum);
    }

    @Test
    void givenArrayWithAllNegativeNumbersWhenMaximumSubarrayThenReturnsExpectedResult() {
        //given
        var arr = new int[] { -8, -7, -5, -4, -3, -1, -2 };
        //when
        var algorithm = new KadaneAlgorithm();
        var maxSum = algorithm.maxSubArraySum(arr);
        //then
        assertEquals(-1, maxSum);
    }

    @Test
    void givenArrayWithAllPosiitveNumbersWhenMaximumSubarrayThenReturnsExpectedResult() {
        //given
        var arr = new int[] {4, 1, 3, 2};
        //when
        var algorithm = new KadaneAlgorithm();
        var maxSum = algorithm.maxSubArraySum(arr);
        //then
        assertEquals(10, maxSum);
    }

    @Test
    void givenArrayToTestStartIndexWhenMaximumSubarrayThenReturnsExpectedResult() {
        //given
        var arr = new int[] { 1, 2, -1, 3, -6, -2 };
        //when
        var algorithm = new KadaneAlgorithm();
        var maxSum = algorithm.maxSubArraySum(arr);
        //then
        assertEquals(5, maxSum);
    }

}