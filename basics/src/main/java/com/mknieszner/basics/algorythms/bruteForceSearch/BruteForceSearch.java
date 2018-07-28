package com.mknieszner.basics.algorythms.bruteForceSearch;

import java.util.function.Function;

public class BruteForceSearch {

    public static double bruteForceSearch(double startX, double endX, double dx, Function<Double, Double> function) {
        double max = function.apply(startX);

        for (double i = startX; i < endX; i = i + dx) {
            if (function.apply(i) > max) {
                max = function.apply(i);
            }
        }
        return max;

    }
}
