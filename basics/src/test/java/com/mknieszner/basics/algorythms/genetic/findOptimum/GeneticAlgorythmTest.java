package com.mknieszner.basics.algorythms.genetic.findOptimum;

import javafx.util.Pair;
import org.junit.Test;

public class GeneticAlgorythmTest {

    GeneticAlgorythm geneticAlgorythm;
    Population population;

    @Test
    public void geneticAlgorithmTest() {
        geneticAlgorythm = new GeneticAlgorythm();

        Pair<Double, Double> result = geneticAlgorythm.solve((x) -> Math.sin(x) * ((x - 2) * (x - 2)) + 3, Constants.SIMULATION_LENGTH);

        System.out.println(String.format("Optimum: f(%f) = %f", result.getKey(), result.getValue()));
    }
}