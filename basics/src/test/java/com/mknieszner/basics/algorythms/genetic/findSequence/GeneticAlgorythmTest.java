package com.mknieszner.basics.algorythms.genetic.findSequence;

import org.junit.Test;

public class GeneticAlgorythmTest {

    GeneticAlgorythm geneticAlgorythm;
    Population population;

    @Test
    public void geneticAlgorithmTest() {
        geneticAlgorythm = new GeneticAlgorythm();
        population = new Population(100);
        population.initiialize();
        int generationCounter = 0;

        while (population.getFittestIndividual().getFitness() != Constants.MAX_FITNESS) {
            ++generationCounter;
            System.out.println("Generation: " + generationCounter + " - fittest is : " + population.getFittestIndividual());
            System.out.println(population.getFittestIndividual() + "\n");
            population = geneticAlgorythm.evolvePopulation(population);
        }

        System.out.println("Solution found !!!");
        System.out.println(population.getFittestIndividual());
    }
}