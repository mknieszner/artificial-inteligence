package com.mknieszner.basics.algorythms.genetic.findSequence;

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

    public Individual getFittestIndividual() {
        Individual fitest = individuals[0];

        for (int i = 1; i < individuals.length; ++i) {
            if (getIndividual(i).getFitness() >= fitest.getFitness()) {
                fitest = getIndividual(i);
            }
        }
        return fitest;
    }

    public int getSize() {
        return this.individuals.length;
    }

}
