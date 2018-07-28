package com.mknieszner.basics.algorythms.hillClimbing;

import javafx.util.Pair;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HillClimbingTestSuite {

    @Test
    public void hillClimbingTest() {
        Pair<Double, Double> result = HillClimbing.hillClimbing(-2, 0.01, x -> -1 * (x - 1) * (x - 1) + 2);
        assertEquals(2.0, result.getValue(), 0.01);
    }
}
