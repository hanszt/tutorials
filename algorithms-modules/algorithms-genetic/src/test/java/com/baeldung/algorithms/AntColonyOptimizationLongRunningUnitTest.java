package com.baeldung.algorithms;


import com.baeldung.algorithms.ga.ant_colony.AntColonyOptimization;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AntColonyOptimizationLongRunningUnitTest {

    @Test
    void testGenerateRandomMatrix() {
        var antTSP = new AntColonyOptimization(5, new Random(0));

        final var matrix = Arrays.stream(antTSP.generateRandomMatrix(5))
                .map(Arrays::toString)
                .collect(joining(System.lineSeparator()));

        final var expected = """
                [61.0, 4.0, 83.0, 93.0, 24.0]
                [46.0, 46.0, 38.0, 88.0, 3.0]
                [63.0, 26.0, 54.0, 39.0, 36.0]
                [61.0, 76.0, 56.0, 31.0, 99.0]
                [92.0, 75.0, 37.0, 13.0, 63.0]""";

        assertEquals(expected, matrix);
    }

    @Test
    void testStartAntOptimization() {
        var antTSP = new AntColonyOptimization(5, new Random(0));

        final var solve = Arrays.stream(antTSP.solve()).boxed().toList();

        assertEquals(List.of(3, 0, 4, 2, 1), solve);
    }

}
