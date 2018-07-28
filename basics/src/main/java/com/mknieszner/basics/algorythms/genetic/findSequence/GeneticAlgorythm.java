package com.mknieszner.basics.algorythms.genetic.findSequence;

import java.util.Random;

public class GeneticAlgorythm {
    private Random randomGenerator;

    public GeneticAlgorythm() {
        this.randomGenerator = new Random();
    }

    public Population evolvePopulation(Population population) {
        Population newPopulation = new Population(population.getSize());

        for (int i = 0; i < population.getSize(); ++i) {

            Individual firstIndividual = randomSelection(population);
            Individual secondIndividual = randomSelection(population);
            Individual newIndividual = crossover(firstIndividual, secondIndividual);
            newPopulation.saveIndividual(i, newIndividual);
        }

        for (int i = 0; i < newPopulation.getSize(); ++i) {
            mutate(newPopulation.getIndividual(i));
        }
        return newPopulation;
    }

    private Individual randomSelection(Population population) {
        Population newPopulation = new Population(Constants.TOURNAMENT_SIZE);
        for (int i = 0; i < Constants.TOURNAMENT_SIZE; ++i) {
            int randomIndex = (int) (Math.random() * population.getSize());
            newPopulation.saveIndividual(i, population.getIndividual(randomIndex));
        }

        return newPopulation.getFittestIndividual();
    }

    private Individual crossover(Individual firstIndividual, Individual secondIndividual) {
        Individual newSolution = new Individual();

        for (int i = 0; i < Constants.CROMOSOME_LENGTH; ++i) {
            if (Math.random() <= Constants.CROSSOVER_RATE) {
                newSolution.setGene(i, firstIndividual.getGene(i));
            } else {
                newSolution.setGene(i, secondIndividual.getGene(i));
            }
        }
        return newSolution;
    }


    private void mutate(Individual individual) {
        for (int i = 0; i < Constants.CROMOSOME_LENGTH; ++i) {
            if (Math.random() < Constants.MUTATION_RATE) {
                int gene = randomGenerator.nextInt(10);
                individual.setGene(i, gene);
            }
        }
    }


}
