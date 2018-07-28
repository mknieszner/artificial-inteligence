package com.mknieszner.basics.algorythms.simulatedAnnealing.travelingSalesman;

import org.junit.Test;

public class SimulatedAnnealingTestSuite {

    SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing();

    @Test
    public void simulatedAnnealingTest() {

        for (int i = 0; i < 100; ++i) {
            Repository.addCity(new City());
        }

        simulatedAnnealing.simulation();

        System.out.println(simulatedAnnealing.getbestSolution().getDistance());
    }

}