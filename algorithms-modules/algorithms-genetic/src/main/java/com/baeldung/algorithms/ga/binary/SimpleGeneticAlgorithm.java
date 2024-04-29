package com.baeldung.algorithms.ga.binary;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public final class SimpleGeneticAlgorithm {

    private static final double UNIFORM_RATE = 0.5;
    private static final double MUTATION_RATE = 0.025;
    private static final int TOURNAMENT_SIZE = 5;
    private static final boolean ELITISM = true;

    private final byte[] solution;
    private final RandomGenerator randomGenerator;

    public SimpleGeneticAlgorithm(RandomGenerator randomGenerator, String solution) {
        this.solution = solutionAsBytes(solution);
        this.randomGenerator = randomGenerator;
    }

    public List<String> runAlgorithm(int populationSize) {
        Population myPop = new Population(populationSize, true, randomGenerator);

        final var builder = new ArrayList<String>();
        int generationCount = 1;
        while (true) {
            final var fittest = myPop.getFittest(this::compareWithSolution);
            if (!(fittest.updateIfZero(compareWithSolution(fittest)) < getMaxFitness())) {
                break;
            }
            final var fittest1 = myPop.getFittest(this::compareWithSolution);
            builder.add("Generation: " + generationCount + " Correct genes found: " + fittest1.updateIfZero(compareWithSolution(fittest1)));
            myPop = evolvePopulation(myPop);
            generationCount++;
        }
        builder.add("Solution found!");
        builder.add("Generation: " + generationCount);
        builder.add("Genes:");
        builder.add(myPop.getFittest(this::compareWithSolution).toString());
        return builder;
    }

    public Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.individuals().size(), false, randomGenerator);

        final int elitismOffset;
        if (ELITISM) {
            newPopulation.individuals().addFirst(pop.getFittest(this::compareWithSolution));
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }

        for (int i = elitismOffset; i < pop.individuals().size(); i++) {
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.individuals().add(i, newIndiv);
        }

        for (int i = elitismOffset; i < newPopulation.individuals().size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    private Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual(randomGenerator);
        for (int i = 0; i < newSol.defaultGeneLength; i++) {
            if (randomGenerator.nextDouble() <= UNIFORM_RATE) {
                newSol.setSingleGene(i, indiv1.getSingleGene(i));
            } else {
                newSol.setSingleGene(i, indiv2.getSingleGene(i));
            }
        }
        return newSol;
    }

    private void mutate(Individual individual) {
        for (int i = 0; i < individual.defaultGeneLength; i++) {
            if (randomGenerator.nextDouble() <= MUTATION_RATE) {
                byte gene = (byte) Math.round(randomGenerator.nextDouble());
                individual.setSingleGene(i, gene);
            }
        }
    }

    private Individual tournamentSelection(Population pop) {
        Population tournament = new Population(TOURNAMENT_SIZE, false, randomGenerator);
        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int randomId = (int) (randomGenerator.nextDouble() * pop.individuals().size());
            tournament.individuals().add(i, pop.getIndividual(randomId));
        }
        return tournament.getFittest(this::compareWithSolution);
    }

    int compareWithSolution(Individual individual) {
        int fitness = 0;
        for (int i = 0; i < individual.defaultGeneLength && i < solution.length; i++) {
            if (individual.getSingleGene(i) == solution[i]) {
                fitness++;
            }
        }
        return fitness;
    }

    private int getMaxFitness() {
        return solution.length;
    }

    private static byte[] solutionAsBytes(String solution) {
        final var bytes = new byte[solution.length()];
        for (int i = 0; i < solution.length(); i++) {
            String character = solution.substring(i, i + 1);
            if (character.contains("0") || character.contains("1")) {
                bytes[i] = Byte.parseByte(character);
            } else {
                bytes[i] = 0;
            }
        }
        return bytes;
    }

}
