package com.mknieszner.basics.algorythms.tabuSearch;

import java.util.List;

public class TabuSearch {

    private State[][] states;
    private TabuList tabuList;
    private NeighborSolutionHandler neighborSolutionHandler;

    public TabuSearch(State[][] states) {
        this.states = states;
        this.tabuList = new TabuList();
        this.neighborSolutionHandler = new NeighborSolutionHandler();
    }

    public State solve(State initialSolution) {
        State bestState = initialSolution;
        State currentState = initialSolution;

        int iterationCounter = 0;

        while (iterationCounter < Constants.NUM_ITERATIONS) {
            List<State> candidateNeighbors = currentState.getNeighbors();
            List<State> solutionsTabu = tabuList.getTabuItems();

            State bestNeighborFound = neighborSolutionHandler.getBestNeighbor(states, candidateNeighbors, solutionsTabu);

            if (bestNeighborFound.getZ() < bestState.getZ()) {
                bestState = bestNeighborFound;
            }

            tabuList.add(currentState);

            currentState = bestNeighborFound;

            iterationCounter++;
        }

        return bestState;
    }
}
