package com.mknieszner.basics.algorythms.genetic.findOptimum;

import java.util.function.Function;

public class Population {
    private Individual[] individuals;

    public Population(int populationSize) {
        this.individuals = new Individual[populationSize];
    }

    public void initiialize() {
        for (int i = 0; i < individuals.length; ++i) {
            Individual individual = new Individual();
            individual.generateIndividual();
            saveIndividual(i, individual);
        }
    }

    public void saveIndividual(int index, Individual individual) {
        this.individuals[index] = individual;
    }

    public Individual getIndividual(int index) {
        return individuals[index];
    }

    public Individual getFittestIndividual(Function<Double, Double> function) {
        Individual fittest = individuals[0];

        for (int i = 1; i < individuals.length; ++i) {
            /*
             * >= for maximum of a function
             *  < for minimum of a function
             */
            if (getIndividual(i).getFitness(function) >= fittest.getFitness(function)) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    public int getSize() {
        return this.individuals.length;
    }

}
