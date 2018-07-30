package com.mknieszner.neuralNetworks.backPropagationNetwok;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BackPropagationNeuralNetworkTestSuite {
    private BackPropagationNeuralNetwork backPropagationNeuralNetwork;
    private float[][] trainingData = new float[][]{
            new float[]{0, 0},
            new float[]{0, 1},
            new float[]{1, 0},
            new float[]{1, 1},
    };

    @Before
    public void before() {
        backPropagationNeuralNetwork = new BackPropagationNeuralNetwork(2, 3, 1);
    }

    @Test
    public void backPropagationNeuralNetworkANDRelationTest() {
        float[][] trainingResults = new float[][]{
                new float[]{0},
                new float[]{0},
                new float[]{0},
                new float[]{1},
        };

        train(trainingData, trainingResults);

        for (int i = 0; i < trainingResults.length; i++) {
            assertEquals(backPropagationNeuralNetwork.run(trainingData[i])[0], trainingResults[i][0], 0.2);
        }
    }

    @Test
    public void backPropagationNeuralNetworkXORRelationTest() { // non linear problem !
        float[][] trainingResults = new float[][]{
                new float[]{0},
                new float[]{1},
                new float[]{1},
                new float[]{0},
        };

        train(trainingData, trainingResults);

        for (int i = 0; i < trainingResults.length; i++) {
            assertEquals(backPropagationNeuralNetwork.run(trainingData[i])[0], trainingResults[i][0], 0.2);
        }
    }

    @Test
    public void backPropagationNeuralNetworkORRelationTest() {
        float[][] trainingResults = new float[][]{
                new float[]{0},
                new float[]{1},
                new float[]{1},
                new float[]{1},
        };

        train(trainingData, trainingResults);

        for (int i = 0; i < trainingResults.length; i++) {
            assertEquals(backPropagationNeuralNetwork.run(trainingData[i])[0], trainingResults[i][0], 0.2);
        }
    }

    @Test
    public void backPropagationNeuralNetworkClusteringTest() {

        // 2 input neurons (x,y),
        // 5 hidden neurons
        // 3 output neurons (3 regions Yellow, Blue, Green)
        backPropagationNeuralNetwork = new BackPropagationNeuralNetwork(2, 3, 1);

        float[][] trainingData = new float[][]{

                // YELLOW CIRCLES 1 -> (1,0,0)
                new float[]{0.1f, 0.2f},
                new float[]{0.3f, 0.2f},
                new float[]{0.15f, 0.58f},
                new float[]{0.45f, 0.7f},
                new float[]{0.4f, 0.9f},

                // GREEN CIRCLES 2 -> (0,1,0)
                new float[]{0.4f, 1.2f},
                new float[]{0.45f, 0.95f},
                new float[]{0.42f, 1f},
                new float[]{0.5f, 1.1f},
                new float[]{0.52f, 1.45f},

                // BLUE CIRCLES 3 -> (0,0,1)
                new float[]{0.6f, 0.2f},
                new float[]{0.75f, 0.7f},
                new float[]{0.9f, 0.34f},
                new float[]{0.85f, 0.76f},
                new float[]{0.8f, 0.34f}
        };

        float[][] trainingResults = new float[][]{
                new float[]{1, 0, 0},
                new float[]{1, 0, 0},
                new float[]{1, 0, 0},
                new float[]{1, 0, 0},
                new float[]{1, 0, 0},
                new float[]{0, 1, 0},
                new float[]{0, 1, 0},
                new float[]{0, 1, 0},
                new float[]{0, 1, 0},
                new float[]{0, 1, 0},
                new float[]{0, 0, 1},
                new float[]{0, 0, 1},
                new float[]{0, 0, 1},
                new float[]{0, 0, 1},
                new float[]{0, 0, 1}
        };

        train(trainingData, trainingResults);

        float[] result = backPropagationNeuralNetwork.run(new float[]{1f, 0.5f});

        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i], new float[]{0, 0, 1}[i], 0.2); // BLUE CIRCLE
        }

         result = backPropagationNeuralNetwork.run(new float[]{0.11f,0.12f});

        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i], new float[]{1, 0, 0}[i], 0.2);  // YELLOW CIRCLE
        }
    }

    private void train(float[][] trainingData, float[][] trainingResults) {
        for (int iteration = 0; iteration < NeuralNetworkConstants.ITERATIONS; iteration++) {
            for (int i = 0; i < trainingResults.length; i++) {
                backPropagationNeuralNetwork.train(
                        trainingData[i],
                        trainingResults[i],
                        NeuralNetworkConstants.LEARNING_RATE,
                        NeuralNetworkConstants.MOMENTUM
                );
            }
        }
    }


}
