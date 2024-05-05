package com.baeldung.algorithms.ga.ant_colony;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;

public class AntColonyOptimization {

    private static final double c = 1.0;
    private static final double alpha = 1;
    private static final double beta = 5;
    private static final double evaporation = 0.5;
    private static final double Q = 500;
    private static final double antFactor = 0.8;
    private static final double randomFactor = 0.01;
    private static final int maxIterations = 1000;

    private final int numberOfCities;
    private final int numberOfAnts;
    private final double[][] graph;
    private final double[][] trails;
    private final List<Ant> ants = new ArrayList<>();
    private final RandomGenerator random;
    private final double[] probabilities;

    private int currentIndex;

    private int[] bestTourOrder;
    private double bestTourLength;

    public AntColonyOptimization(int noOfCities, RandomGenerator random) {
        this.random = random;
        graph = generateRandomMatrix(noOfCities);
        numberOfCities = graph.length;
        numberOfAnts = (int) (numberOfCities * antFactor);

        trails = new double[numberOfCities][numberOfCities];
        probabilities = new double[numberOfCities];
        IntStream.range(0, numberOfAnts)
            .forEach(i -> ants.add(new Ant(numberOfCities)));
    }

    /**
     * Generate initial solution
     */
    public double[][] generateRandomMatrix(int n) {
        return IntStream.range(0, n)
                .mapToObj(i -> IntStream.range(0, n)
                        .mapToDouble(j -> Math.abs(random.nextInt(100) + 1))
                        .toArray())
                .toArray(double[][]::new);
    }

    /**
     * Perform ant optimization
     */
    public void startAntOptimization() {
        IntStream.rangeClosed(1, 3)
            .forEach(i -> {
                System.out.println("Attempt #" + i);
                solve();
            });
    }

    /**
     * Use this method to run the main logic
     */
    public int[] solve() {
        setupAnts();
        clearTrails();
        IntStream.range(0, maxIterations)
            .forEach(i -> {
                moveAnts();
                updateTrails();
                updateBest();
            });
        System.out.println("Best tour length: " + (bestTourLength - numberOfCities));
        System.out.println("Best tour order: " + Arrays.toString(bestTourOrder));
        return Arrays.copyOf(bestTourOrder, bestTourOrder.length);
    }

    /**
     * Prepare ants for the simulation
     */
    private void setupAnts() {
        IntStream.range(0, numberOfAnts)
            .forEach(i -> ants.forEach(ant -> {
                ant.clear();
                ant.visitCity(-1, random.nextInt(numberOfCities));
            }));
        currentIndex = 0;
    }

    /**
     * At each iteration, move ants
     */
    private void moveAnts() {
        IntStream.range(currentIndex, numberOfCities - 1)
            .forEach(i -> {
                ants.forEach(ant -> ant.visitCity(currentIndex, selectNextCity(ant)));
                currentIndex++;
            });
    }

    /**
     * Select next city for each ant
     */
    private int selectNextCity(Ant ant) {
        var t = random.nextInt(numberOfCities - currentIndex);
        if (random.nextDouble() < randomFactor) {
            var cityIndex = IntStream.range(0, numberOfCities)
                .filter(i -> i == t && !ant.visited(i))
                .findFirst();
            if (cityIndex.isPresent()) {
                return cityIndex.getAsInt();
            }
        }
        calculateProbabilities(ant);
        var r = random.nextDouble();
        double total = 0;
        for (var i = 0; i < numberOfCities; i++) {
            total += probabilities[i];
            if (total >= r) {
                return i;
            }
        }

        throw new RuntimeException("There are no other cities");
    }

    /**
     * Calculate the next city picks probabilities
     */
    public void calculateProbabilities(Ant ant) {
        var i = ant.trail[currentIndex];
        var pheromone = 0.0;
        for (var l = 0; l < numberOfCities; l++) {
            if (!ant.visited(l)) {
                pheromone += Math.pow(trails[i][l], alpha) * Math.pow(1.0 / graph[i][l], beta);
            }
        }
        for (var j = 0; j < numberOfCities; j++) {
            if (ant.visited(j)) {
                probabilities[j] = 0.0;
            } else {
                var numerator = Math.pow(trails[i][j], alpha) * Math.pow(1.0 / graph[i][j], beta);
                probabilities[j] = numerator / pheromone;
            }
        }
    }

    /**
     * Update trails that ants used
     */
    private void updateTrails() {
        for (var i = 0; i < numberOfCities; i++) {
            for (var j = 0; j < numberOfCities; j++) {
                trails[i][j] *= evaporation;
            }
        }
        for (var a : ants) {
            var contribution = Q / a.trailLength(graph);
            for (var i = 0; i < numberOfCities - 1; i++) {
                trails[a.trail[i]][a.trail[i + 1]] += contribution;
            }
            trails[a.trail[numberOfCities - 1]][a.trail[0]] += contribution;
        }
    }

    /**
     * Update the best solution
     */
    private void updateBest() {
        if (bestTourOrder == null) {
            final var first = ants.getFirst();
            bestTourOrder = first.trail;
            bestTourLength = first
                .trailLength(graph);
        }
        for (var a : ants) {
            if (a.trailLength(graph) < bestTourLength) {
                bestTourLength = a.trailLength(graph);
                bestTourOrder = a.trail.clone();
            }
        }
    }

    /**
     * Clear trails after simulation
     */
    private void clearTrails() {
        IntStream.range(0, numberOfCities)
            .forEach(i -> IntStream.range(0, numberOfCities)
                .forEach(j -> trails[i][j] = c));
    }

}
