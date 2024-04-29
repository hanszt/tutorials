package com.baeldung.algorithms.ga.annealing;

import java.util.random.RandomGenerator;

public final class SimulatedAnnealing {

    private final RandomGenerator randomGenerator;
    private final Travel travel;

    public SimulatedAnnealing(RandomGenerator randomGenerator, Travel travel) {
        this.randomGenerator = randomGenerator;
        this.travel = travel;
    }

    public double simulateAnnealing(double startingTemperature, int numberOfIterations, double coolingRate) {
        System.out.println("Starting SA with temperature: " + startingTemperature + ", # of iterations: " + numberOfIterations + " and colling rate: " + coolingRate);
        double t = startingTemperature;

        double bestDistance = travel.getDistance();
        System.out.println("Initial distance of travel: " + bestDistance);
        Travel currentSolution = travel;

        for (int i = 0; i < numberOfIterations; i++) {
            if (t > 0.1) {
                currentSolution.swapCities(randomGenerator);
                double currentDistance = currentSolution.getDistance();
                if (currentDistance < bestDistance) {
                    bestDistance = currentDistance;
                } else if (Math.exp((bestDistance - currentDistance) / t) < randomGenerator.nextDouble()) {
                    currentSolution.revertSwap();
                }
                t *= coolingRate;
            } else {
                continue;
            }
            if (i % 100 == 0) {
                System.out.println("Iteration #" + i);
            }
        }
        return bestDistance;
    }

}
