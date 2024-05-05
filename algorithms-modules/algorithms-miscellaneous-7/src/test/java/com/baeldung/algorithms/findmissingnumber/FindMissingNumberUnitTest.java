package com.baeldung.algorithms.findmissingnumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.BitSet;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FindMissingNumberUnitTest {

    private int[] numbers;
    int N;

    @BeforeEach
    void setup() {
        numbers = new int[] { 1, 4, 5, 2, 7, 8, 6, 9 };
        N = numbers.length + 1;
    }

    @Test
    void givenIntegersArray_whenUseArithmeticSumToFindMissingNumber_thenGetMissingNumber() {
        var expectedSum = (N * (N + 1)) / 2;
        var actualSum = Arrays.stream(numbers).sum();

        var missingNumber = expectedSum - actualSum;
        assertEquals(3, missingNumber);
    }

    @Test
    void givenIntegersArray_whenUseXorToFindMissingNumber_thenGetMissingNumber() {
        var xorValue = IntStream.rangeClosed(1, N).reduce(0, (a, b) -> a ^ b);

        xorValue = Arrays.stream(numbers).reduce(xorValue, (x, y) -> x ^ y);

        assertEquals(3, xorValue);
    }

    @Test
    void givenIntegersArray_whenUseSortingToFindMissingNumber_thenGetMissingNumber() {
        Arrays.sort(numbers);

        var missingNumber = -1;
        for (var index = 0; index < numbers.length; index++) {
            if (numbers[index] != index + 1) {
                missingNumber = index + 1;
                break;
            }
        }

        assertEquals(3, missingNumber);
    }

    @Test
    void givenIntegersArray_whenTrackUsingBooleanArrayToFindMissingNumber_thenGetMissingNumber() {
        var present = new boolean[N];

        var missingNumber = -1;
        Arrays.stream(numbers).forEach(number -> present[number - 1] = true);

        for (var index = 0; index < present.length; index++) {
            if (!present[index]) {
                missingNumber = index + 1;
                break;
            }
        }

        assertEquals(3, missingNumber);
    }

    @Test
    void givenIntegersArray_whenUseBitSetToFindMissingNumber_thenGetMissingNumber() {
        var bitSet = new BitSet(N);
        for (var num : numbers) {
            bitSet.set(num);
        }

        var missingNumber = bitSet.nextClearBit(1);

        assertEquals(3, missingNumber);
    }
}
