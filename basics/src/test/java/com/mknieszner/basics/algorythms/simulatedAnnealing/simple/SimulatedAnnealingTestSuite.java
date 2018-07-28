package com.mknieszner.basics.algorythms.simulatedAnnealing.simple;

import javafx.util.Pair;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimulatedAnnealingTestSuite {

    SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing();

    @Test
    public void SimulatedAnnealingTest() {
        Pair<Double, Double> optimum =
                simulatedAnnealing.findOptimum((x) -> (x - 0.3) * (x - 0.3) * (x - 0.3) - 5 * x + x * x - 2);

        assertEquals(optimum.getKey(), 1.22, 0.1);
        assertEquals(optimum.getValue(), -5.83, 0.1);
    }

}