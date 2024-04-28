package com.baeldung.algorithms.ga.binary;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record Population(List<Individual> individuals) {

    public Population(int size, boolean createNew) {
        this(getIndividuals(size, createNew));
    }

    private static List<Individual> getIndividuals(int size, boolean createNew) {
        if (createNew) {
            return createNewPopulation(size);
        }
        return new ArrayList<>();
    }

    public Individual getIndividual(int index) {
        return individuals.get(index);
    }

    public Individual getFittest() {
        Individual fittest = individuals.getFirst();
        for (int i = 0; i < individuals.size(); i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    private static List<Individual> createNewPopulation(int size) {
        final var individuals = new ArrayList<Individual>(size);
        for (int i = 0; i < size; i++) {
            Individual newIndividual = new Individual();
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
