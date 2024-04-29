package com.baeldung.algorithms;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.baeldung.algorithms.ga.annealing.City;
import com.baeldung.algorithms.ga.annealing.Travel;
import org.junit.jupiter.api.Test;

import com.baeldung.algorithms.ga.annealing.SimulatedAnnealing;

import java.util.Random;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

class SimulatedAnnealingLongRunningUnitTest {

    @Test
    void testSimulateAnnealing() {
        final var random = new Random(0);
        final var numberOfCities = 10;
        final var startingTemperature = 10;
        final var numberOfIterations = 1000;
        final var coolingRate = 0.9;
        final var travel = new Travel(numberOfCities, i -> new City(random.nextInt(500), random.nextInt(500)));

        final var annealing = new SimulatedAnnealing(random, travel)
                .simulateAnnealing(startingTemperature, numberOfIterations, coolingRate);

        assertEquals(1963.0, annealing);
    }

}
