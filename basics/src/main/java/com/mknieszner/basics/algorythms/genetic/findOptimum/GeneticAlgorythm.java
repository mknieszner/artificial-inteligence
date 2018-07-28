package com.mknieszner.basics.algorythms.genetic.findOptimum;

import javafx.util.Pair;

import java.util.Random;
import java.util.function.Function;

public class GeneticAlgorythm {
    private Random randomGenerator;
    private Population population;


    public GeneticAlgorythm() {
        this.randomGenerator = new Random();
    }

    public Pair<Double, Double> solve(Function<Double, Double> function, int numberOfIterations) {
        population = new Population(100);
        population.initiialize();
        int generationCounter = 0;

        while (generationCounter != numberOfIterations) {
            ++generationCounter;
            population = evolvePopulation(population, function);
        }

        double optimum = population.getFittestIndividual(function).genesToDouble();
        return new Pair<>(optimum, function.apply(optimum));
    }

    private Population evolvePopulation(Population population, Function<Double, Double> function) {
        Population newPopulation = new Population(population.getSize());

        for (int i = 0; i < population.getSize(); ++i) {

            Individual firstIndividual = randomSelection(population, function);
            Individual secondIndividual = randomSelection(population, function);
            Individual newIndividual = crossover(firstIndividual, secondIndividual);
            newPopulation.saveIndividual(i, newIndividual);
        }

        for (int i = 0; i < newPopulation.getSize(); ++i) {
            mutate(newPopulation.getIndividual(i));
        }
        return newPopulation;
    }

    private Individual randomSelection(Population population, Function<Double, Double> function) {
        Population newPopulation = new Population(Constants.TOURNAMENT_SIZE);
        for (int i = 0; i < Constants.TOURNAMENT_SIZE; ++i) {
            int randomIndex = (int) (Math.random() * population.getSize());
            newPopulation.saveIndividual(i, population.getIndividual(randomIndex));
        }

        Individual fittestIndividual = newPopulation.getFittestIndividual(function);

        return fittestIndividual;
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
