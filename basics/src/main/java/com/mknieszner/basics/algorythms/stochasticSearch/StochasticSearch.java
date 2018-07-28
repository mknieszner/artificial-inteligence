package com.mknieszner.basics.algorythms.stochasticSearch;

import javafx.util.Pair;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

public class StochasticSearch {

    public static Pair<Double, Double> stochasticSearch(double startX, double endX, Function<Double, Double> function) {
        Random random = new Random();
        double minX = startX;
        double min = function.apply(startX);

        for (int i = 0; i < 10000; ++i) {

            double randomX = ThreadLocalRandom.current().nextDouble(startX, endX);

            if (function.apply(randomX) < min) {
                min = function.apply(randomX);
                minX = randomX;
            }
        }
        return new Pair<>(minX, min);
    }
}
