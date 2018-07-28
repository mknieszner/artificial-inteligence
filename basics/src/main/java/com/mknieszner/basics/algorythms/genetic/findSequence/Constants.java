package com.mknieszner.basics.algorythms.genetic.findSequence;

public class Constants {
    private Constants() {
    }

    /**
     * Represents solution we are looking for
     */
    public static final int[] SOLUTION_SEQUENCE = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static final double CROSSOVER_RATE = 0.5;
    public static final double MUTATION_RATE = 0.15;
    public static final int TOURNAMENT_SIZE = 5;
    public static final int CROMOSOME_LENGTH = 10;
    public static final int MAX_FITNESS = 10;
}
