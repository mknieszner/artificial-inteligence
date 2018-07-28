package com.mknieszner.basics.algorythms.tabuSearch;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

@Getter
public class State {
    private final double x;
    private final double y;
    private final double z;
    private List<State> neighbors;

    public State(double x, double y, BiFunction<Double, Double, Double> function) {
        this.x = x;
        this.y = y;
        this.z = function.apply(x, y);
        neighbors = new ArrayList<>();
    }

    public void addNeighbor(State state) {
        this.neighbors.add(state);
    }

    @Override
    public String toString() {
        return this.x + " " + this.y + " " + this.z;
    }
}


