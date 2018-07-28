package com.mknieszner.basics.algorythms.genetic.findSequence;

import java.util.Random;

/**
 * Represents possible solution
 */
public class Individual {
    /**
     * Represents chromosome
     */
    private int[] genes;
    private int fitness;
    private Random randomGenerator;

    public Individual() {
        this.genes = new int[Constants.CROMOSOME_LENGTH];
        randomGenerator = new Random();
    }

    public void generateIndividual() {
        for (int i = 0; i < Constants.CROMOSOME_LENGTH; ++i) {
            int gene = randomGenerator.nextInt(10);
            genes[i] = gene;
        }
    }

    public int getFitness() {
        if (fitness == 0) {
            for (int i = 0; i < Constants.CROMOSOME_LENGTH; ++i) {
                if (getGene(i) == Constants.SOLUTION_SEQUENCE[i]) {
                    this.fitness++;
                }
            }
        }
        return fitness;
    }

    public int getGene(int index) {
        return this.genes[index];
    }

    public void setGene(int index, int gene) {
        this.genes[index] = gene;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < Constants.CROMOSOME_LENGTH; ++i) {
            s.append(getGene(i));
        }
        return s.toString();
    }
}
