package com.mknieszner.basics.algorythms.particleSwarmOptimization;

import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class ParticleSwarmOptimizationTest {

    @Test
    public void ParticleSwarmOptimization() {
        Function<double[], Double> function =
                (data) -> Math.exp(-data[0] * data[0] - data[1] * data[1]) * Math.sin(data[0]);
        ParticleSwarmOptimization particleSwarmOptimization = new ParticleSwarmOptimization();

        double[] arguments = particleSwarmOptimization.solve(function);

        assertEquals(-0.65, arguments[0], 0.01);
        assertEquals(0, arguments[1], 0.01);
        assertEquals(-0.39, function.apply(arguments), 0.01);
    }

}