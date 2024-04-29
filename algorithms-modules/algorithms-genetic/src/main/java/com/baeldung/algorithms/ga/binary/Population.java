package com.baeldung.algorithms.ga.binary;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.ToIntFunction;
import java.util.random.RandomGenerator;

public record Population(List<Individual> individuals) {

    public Population(int size, boolean createNew, RandomGenerator random) {
        this(getIndividuals(size, createNew, random));
    }

    private static List<Individual> getIndividuals(int size, boolean createNew, RandomGenerator randomGenerator) {
        if (createNew) {
            return createNewPopulation(randomGenerator, size);
        }
        return new ArrayList<>();
    }

    public Individual getIndividual(int index) {
        return individuals.get(index);
    }

    public Individual getFittest(ToIntFunction<Individual> fitnessFunction) {
        Individual fittest = individuals.getFirst();
        for (int i = 0; i < individuals.size(); i++) {
            final var individual = getIndividual(i);
            if (getNewFitness(fittest, fitnessFunction) <= getNewFitness(individual, fitnessFunction)) {
                fittest = individual;
            }
        }
        return fittest;
    }

    private static int getNewFitness(Individual fittest, ToIntFunction<Individual> fitnessFunction) {
        return fittest.updateIfZero(fitnessFunction.applyAsInt(fittest));
    }

    private static List<Individual> createNewPopulation(RandomGenerator randomGenerator, int size) {
        final var individuals = new ArrayList<Individual>(size);
        for (int i = 0; i < size; i++) {
            Individual newIndividual = new Individual(randomGenerator);
            individuals.add(i, newIndividual);
        }
        return individuals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof final Population that)) return false;
        return Objects.equals(individuals, that.individuals);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(individuals);
    }

    @Override
    public String toString() {
        return "Population{" +
               "individuals=" + individuals +
               '}';
    }
}
