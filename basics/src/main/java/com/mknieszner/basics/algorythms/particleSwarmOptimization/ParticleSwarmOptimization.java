package com.mknieszner.basics.algorythms.particleSwarmOptimization;

import java.util.function.Function;

import static com.mknieszner.basics.algorythms.particleSwarmOptimization.Constants.DESTINATION_POSITION;
import static com.mknieszner.basics.algorythms.particleSwarmOptimization.Constants.SOURCE_POSITION;

public class ParticleSwarmOptimization {

    private double[] globalBestSolutions;
    private Particle[] particleSwarm;
    /**
     * how many iterations to make
     */
    private int epoches = 0;

    public ParticleSwarmOptimization() {
        this.globalBestSolutions = new double[Constants.NAM_DIMENSIONS];
        this.particleSwarm = new Particle[Constants.NUM_PARTICLES];
        generateRandomSolution();

    }

    public double[] solve(Function<double[], Double> function) {
        for (int i = 0; i < Constants.NUM_PARTICLES; ++i) {
            double[] locations = initializeLocation();
            double[] velocities = initializeVelocity();
            particleSwarm[i] = new Particle(locations, velocities);
        }

        while (epoches < Constants.MAX_ITERATIONS) {
            for (Particle actualParticle : this.particleSwarm) {
                //update velocity
                for (int i = 0; i < actualParticle.getVelocity().length; ++i) {
                    double rp = Math.random();
                    double rg = Math.random();
                    actualParticle.getVelocity()[i] =
                            Constants.w * actualParticle.getVelocity()[i]
                                    + Constants.c1 * rp * (actualParticle.getBestPosition()[i] - actualParticle.getPosition()[i]
                                    + Constants.c2 * rg * (globalBestSolutions[i] - actualParticle.getPosition()[i]));
                }

                //update positions
                for (int i = 0; i < actualParticle.getPosition().length; ++i) {
                    actualParticle.getPosition()[i] += actualParticle.getVelocity()[i];
                    if (actualParticle.getPosition()[i] < Constants.MIN) {
                        actualParticle.getPosition()[i] = Constants.MIN;
                    } else if (actualParticle.getPosition()[i] > Constants.MAX) {
                        actualParticle.getPosition()[i] = Constants.MAX;

                    }
                }

                if (function.apply(actualParticle.getPosition()) < function.apply(actualParticle.getBestPosition())) {
                    actualParticle.setBestPosition(actualParticle.getPosition());
                }

                if (function.apply(actualParticle.getBestPosition()) < function.apply(globalBestSolutions)) {
                    System.arraycopy(
                            actualParticle.getBestPosition(), SOURCE_POSITION,
                            globalBestSolutions, DESTINATION_POSITION,
                            actualParticle.getBestPosition().length);
                }
            }
            epoches++;
        }
        return this.globalBestSolutions;
    }

    //todo initialize fo bigger arrays
    private double[] initializeLocation() {
        double x = random(Constants.MIN, Constants.MAX);
        double y = random(Constants.MIN, Constants.MAX);

        return new double[]{x, y};
    }

    //todo initialize fo bigger arrays
    private double[] initializeVelocity() {
        double vx = random(-(Constants.MAX - Constants.MIN), Constants.MAX - Constants.MIN);
        double vy = random(-(Constants.MAX - Constants.MIN), Constants.MAX - Constants.MIN);
        return new double[]{vx, vy};

    }

    private void generateRandomSolution() {
        for (int i = 0; i < Constants.NAM_DIMENSIONS; ++i) {
            double randCoordinate = random(Constants.MIN, Constants.MAX);
            this.globalBestSolutions[i] = randCoordinate;
        }
    }

    private double random(double min, double max) {
        return min + (max - min) * Math.random();
    }
}
