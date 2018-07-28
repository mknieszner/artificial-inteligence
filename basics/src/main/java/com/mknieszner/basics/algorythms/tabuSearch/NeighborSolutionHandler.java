package com.mknieszner.basics.algorythms.tabuSearch;

import java.util.List;

public class NeighborSolutionHandler {

    public State getBestNeighbor(final State[][] states,
                                 final List<State> neighborStates,
                                 final List<State> tabuStates) {

        neighborStates.removeAll(tabuStates);

        if (neighborStates.size() == 0) {
            return states[Constants.NUM_VALUES / 2][Constants.NUM_VALUES / 2];
        }

        State bestSolution = neighborStates.get(0);

        for (int i = 1; i < neighborStates.size(); i++) {
            if (neighborStates.get(i).getZ() < bestSolution.getZ()) {
                bestSolution = neighborStates.get(i);
            }
        }

        return bestSolution;
    }
}
