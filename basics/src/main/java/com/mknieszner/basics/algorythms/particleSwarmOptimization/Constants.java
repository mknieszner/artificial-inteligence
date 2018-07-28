package com.mknieszner.basics.algorythms.particleSwarmOptimization;

public class Constants {
    public static final int NAM_DIMENSIONS = 2;
    public static final int NUM_PARTICLES = 10;
    /**
     * start of interval
     */
    public static final double MIN = -2;
    /**
     * end of interval
     */
    public static final double MAX = 2;
    /**
     * inertial weight
     */
    public static final double w = 0.729;
    /**
     * cognitive (local) weight
     */
    public static final double c1 = 1.49;
    /**
     * social (global) weight
     */
    public static final double c2 = 1.49;
    public static final int SOURCE_POSITION = 0;
    public static final int DESTINATION_POSITION = 0;
    public static final int MAX_ITERATIONS = 10000;

    private Constants() {
    }

}
