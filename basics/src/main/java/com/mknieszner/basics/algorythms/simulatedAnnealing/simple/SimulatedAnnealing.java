package com.mknieszner.basics.algorythms.simulatedAnnealing.simple;

import javafx.util.Pair;

import java.util.Random;
import java.util.function.Function;

/**
 * Optimize one argument function
 */
public class SimulatedAnnealing {

    private Random randomGenerator;
    private double currentCoordinateX;
    private double nextCoordinateX;
    private double bestCoordinateX;

    public SimulatedAnnealing() {
        this.randomGenerator = new Random();
    }


    public Pair<Double, Double> findOptimum(Function<Double,Double> function) {

        double temperature = Constants.MAX_TEPERATURE;

        while (temperature > Constants.MIN_TEPERATURE) {
            nextCoordinateX = getRandomX();

            double actualEnergy = getEnergy(currentCoordinateX, function);
            double newEnergy = getEnergy(nextCoordinateX, function);

            //accept worse moves sometimes at beginning when temperature is still high
            if (acceptanceProbability(actualEnergy, newEnergy, temperature) > Math.random()) {
                currentCoordinateX = nextCoordinateX;
            }

            //change < to > to find max
            if (function.apply(currentCoordinateX) < function.apply(bestCoordinateX)) {
                bestCoordinateX = currentCoordinateX;
            }

            //cooling temperature
            temperature *= 1 - Constants.COOLING_RATE;
        }

        return new Pair<>(bestCoordinateX, function.apply(bestCoordinateX));
    }

    private double getRandomX() {
        return randomGenerator.nextDouble() * (Constants.MAX_COORDINATE - Constants.MIN_COORDINATE) + Constants.MIN_COORDINATE;
    }

    private double getEnergy(double x, Function<Double, Double> function) {
        return function.apply(x);
    }

    /**
     * @param energy    - energy of actual state
     * @param newEnergy - energy of neighbouring state state
     * @param temperature - actual temperature
     */
    private double acceptanceProbability(double energy, double newEnergy, double temperature) {

        //better solution - accept solution
        //change < to > to find max
        if (newEnergy < energy) {
            return 1;
        }

        //to avoid local optimum at start it will pass worse solution
        return Math.exp((energy - newEnergy) / temperature);
    }
}
