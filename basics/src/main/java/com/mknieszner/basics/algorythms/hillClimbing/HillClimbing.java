package com.mknieszner.basics.algorythms.hillClimbing;

import javafx.util.Pair;

import java.util.function.Function;

public class HillClimbing {

    public static Pair<Double, Double> hillClimbing(double startX, double dx, Function<Double, Double> function) {
        double actualPointX = startX;
        double max = function.apply(actualPointX);

        while (function.apply(actualPointX) >= max) {
            max = function.apply(actualPointX);
            actualPointX += dx;
        }

        return new Pair<>(actualPointX, max);
    }
}
