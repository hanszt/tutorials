package com.baeldung.algorithms;


import com.baeldung.algorithms.ga.binary.SimpleGeneticAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryGeneticAlgorithmLongRunningUnitTest {

    @Test
    void testGA() {
        final var random = new Random(0);
        final var target = "1011000100000100010000100000100111001000000100000100000000001111";
        var ga = new SimpleGeneticAlgorithm(random, target);

        final var solution = ga.runAlgorithm(50);

        final var expected = """
                Generation: 1 Correct genes found: 42
                Generation: 2 Correct genes found: 45
                Generation: 3 Correct genes found: 48
                Generation: 4 Correct genes found: 50
                Generation: 5 Correct genes found: 54
                Generation: 6 Correct genes found: 56
                Generation: 7 Correct genes found: 59
                Generation: 8 Correct genes found: 59
                Generation: 9 Correct genes found: 60
                Generation: 10 Correct genes found: 61
                Generation: 11 Correct genes found: 62
                Generation: 12 Correct genes found: 62
                Generation: 13 Correct genes found: 62
                Generation: 14 Correct genes found: 63
                Solution found!
                Generation: 15
                Genes:
                1011000100000100010000100000100111001000000100000100000000001111""".stripIndent();

        assertEquals(expected, String.join(System.lineSeparator(), solution));
        assertEquals(target, solution.getLast());
    }

}
