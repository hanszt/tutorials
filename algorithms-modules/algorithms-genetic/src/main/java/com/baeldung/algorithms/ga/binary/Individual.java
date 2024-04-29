package com.baeldung.algorithms.ga.binary;

import java.util.random.RandomGenerator;

public final class Individual {

    final int defaultGeneLength = 64;
    private final byte[] genes = new byte[defaultGeneLength];
    private int fitness = 0;

    public Individual(RandomGenerator randomGenerator) {
        for (int i = 0; i < genes.length; i++) {
            byte gene = (byte) Math.round(randomGenerator.nextDouble());
            genes[i] = gene;
        }
    }

    byte getSingleGene(int index) {
        return genes[index];
    }

    void setSingleGene(int index, byte value) {
        genes[index] = value;
        fitness = 0;
    }

    public int updateIfZero(int fitness) {
        if (this.fitness == 0) {
            this.fitness = fitness;
        }
        return this.fitness;
    }

    @Override
    public String toString() {
        StringBuilder geneString = new StringBuilder();
        for (int i = 0; i < genes.length; i++) {
            geneString.append(getSingleGene(i));
        }
        return geneString.toString();
    }

}
