package com.mknieszner.basics.algorythms.stochasticSearch;

import javafx.util.Pair;
import org.junit.Test;

public class StochasticSearchTestSuite {

    @Test
    public void stochasticSearchTest() {
        Pair<Double, Double> result = StochasticSearch.stochasticSearch(-1, 2, (x) -> (x + 1) * (x + 1) - 3);
        System.out.println(String.format("X=%f Y=%f", result.getKey(), result.getValue()));
    }
}
