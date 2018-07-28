package com.mknieszner.basics.algorythms.genetic.findOptimum;

import java.util.Random;
import java.util.function.Function;

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

    Individual() {
        this.genes = new int[Constants.CROMOSOME_LENGTH];
        randomGenerator = new Random();
    }

    public void generateIndividual() {
        for (int i = 0; i < Constants.CROMOSOME_LENGTH; ++i) {
            int gene = randomGenerator.nextInt(2);
            genes[i] = gene;
        }
    }


    public double getFitness(Function<Double, Double> function) {
        double genesInDouble = genesToDouble();
        return function.apply(genesInDouble);
    }

    public int getGene(int index) {
        return this.genes[index];
    }

    public void setGene(int index, int gene) {
        this.genes[index] = gene;
    }

    public double genesToDouble() {
        int base = 1;
        double geneInDouble = 0;

        for (int i = 0; i < Constants.GENE_LENGTH; ++i) {
            if (this.genes[i] == 1) {
                geneInDouble += base;
            }
            base = base * 2;
        }

        /*
         * [0,10] <-> 2^10 = 1024
         */
        geneInDouble = geneInDouble / 102.4f;

        return geneInDouble;
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
