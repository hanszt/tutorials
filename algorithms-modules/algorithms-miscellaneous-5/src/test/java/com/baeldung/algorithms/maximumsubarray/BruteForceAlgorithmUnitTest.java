package com.baeldung.algorithms.maximumsubarray;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BruteForceAlgorithmUnitTest {

    @Test
    void givenArrayWithNegativeNumberWhenMaximumSubarrayThenReturns6() {
        //given
        var arr = new int[]{-3, 1, -8, 4, -1, 2, 1, -5, 5};
        //when
        var algorithm = new BruteForceAlgorithm();
        var maximumSum = algorithm.maxSubArray(arr);
        //then
        assertEquals(6, maximumSum);
    }
}