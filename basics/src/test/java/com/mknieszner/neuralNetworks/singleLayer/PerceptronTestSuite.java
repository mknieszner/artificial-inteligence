package com.mknieszner.neuralNetworks.singleLayer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PerceptronTestSuite {

    @Test
    public void perceptronTest() {
        float[][] input = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        float[] output = {0, 1, 1, 0};

        Perceptron perceptron = new Perceptron(input, output);
        perceptron.train(0.1f);

        for (int i = 0; i < input.length; i++) {
            assertEquals(output[i], perceptron.calculateOutput(input[i]), 0);
        }
    }
}
