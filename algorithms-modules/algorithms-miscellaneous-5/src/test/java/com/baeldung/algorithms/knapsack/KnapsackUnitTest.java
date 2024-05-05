package com.baeldung.algorithms.knapsack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class KnapsackUnitTest {

    @Test
    void givenWeightsandValues_whenCalculateMax_thenOutputCorrectResult() {
        final var w = new int[] { 23, 26, 20, 18, 32, 27, 29, 26, 30, 27 };
        final var v = new int[] { 505, 352, 458, 220, 354, 414, 498, 545, 473, 543 };
        final var n = 10;
        final var W = 67;
        final var knapsack = new Knapsack();

        assertEquals(1270, knapsack.knapsackRec(w, v, n, W));
        assertEquals(1270, knapsack.knapsackDP(w, v, n, W));
    }

    @Test
    void givenZeroItems_whenCalculateMax_thenOutputZero() {
        final var w = new int[] {};
        final var v = new int[] {};
        final var n = 0;
        final var W = 67;
        final var knapsack = new Knapsack();

        assertEquals(0, knapsack.knapsackRec(w, v, n, W));
        assertEquals(0, knapsack.knapsackDP(w, v, n, W));
    }

    @Test
    void givenZeroWeightLimit_whenCalculateMax_thenOutputZero() {
        final var w = new int[] { 23, 26, 20, 18, 32, 27, 29, 26, 30, 27 };
        final var v = new int[] { 505, 352, 458, 220, 354, 414, 498, 545, 473, 543 };
        final var n = 10;
        final var W = 0;
        final var knapsack = new Knapsack();

        assertEquals(0, knapsack.knapsackRec(w, v, n, W));
        assertEquals(0, knapsack.knapsackDP(w, v, n, W));
    }
}
