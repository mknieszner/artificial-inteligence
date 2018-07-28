package com.mknieszner.basics.algorythms.particleSwarmOptimization;

import java.util.Arrays;

import static com.mknieszner.basics.algorythms.particleSwarmOptimization.Constants.DESTINATION_POSITION;
import static com.mknieszner.basics.algorythms.particleSwarmOptimization.Constants.SOURCE_POSITION;

public class Particle {
    private double[] position; // xi -> [x,y] f.e. point
    private double[] velocity;
    private double[] bestPosition;

    public Particle(double[] position, double[] velocity) {
        this.position = new double[Constants.NAM_DIMENSIONS];
        this.velocity = new double[Constants.NAM_DIMENSIONS];
        this.bestPosition = new double[Constants.NAM_DIMENSIONS];

        System.arraycopy(velocity, SOURCE_POSITION, this.velocity, DESTINATION_POSITION, velocity.length);
        System.arraycopy(position, SOURCE_POSITION, this.position, DESTINATION_POSITION, position.length);
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }

    public double[] getVelocity() {
        return velocity;
    }

    public void setVelocity(double[] velocity) {
        this.velocity = velocity;
    }

    public double[] getBestPosition() {
        return bestPosition;
    }

    public void setBestPosition(double[] bestPosition) {
        this.bestPosition = bestPosition;
    }

    @Override
    public String toString() {
        return "Particle{" +
                "position=" + Arrays.toString(position) +
                ", velocity=" + Arrays.toString(velocity) +
                ", bestPosition=" + Arrays.toString(bestPosition) +
                '}';
    }
}
