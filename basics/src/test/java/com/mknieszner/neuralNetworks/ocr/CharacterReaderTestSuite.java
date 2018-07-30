package com.mknieszner.neuralNetworks.ocr;

import com.mknieszner.neuralNetworks.backPropagationNetwok.BackPropagationNeuralNetwork;
import com.mknieszner.neuralNetworks.backPropagationNetwok.NeuralNetworkConstants;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CharacterReaderTestSuite {
    private CharacterReader reader;
    private BackPropagationNeuralNetwork backPropagationNeuralNetwork;

    @Before
    public void before() {
        backPropagationNeuralNetwork = new BackPropagationNeuralNetwork(64, 15, 10);
        reader = new CharacterReader();
    }

    @Test
    public void characterReaderTest() {
        train(getTrainingData(), getTrainingResults());

        //from training data
        float[] one = getTrainingData()[2];
        float[] two = getTrainingData()[3];

        //simulate other picture
        one[4] = one[4] == 1 ? 0 : 1;
        one[6] = one[6] == 1 ? 0 : 1;
        two[4] = two[4] == 1 ? 0 : 1;
        two[6] = two[6] == 1 ? 0 : 1;

        float[] oneResult = backPropagationNeuralNetwork.run(one);
        float[] twoResult = backPropagationNeuralNetwork.run(two);

        for (int i = 0; i < oneResult.length; i++) {
            assertEquals(getTrainingResults()[2][i], oneResult[i], 0.2);
        }

        for (int i = 0; i < twoResult.length; i++) {
            assertEquals(getTrainingResults()[3][i], twoResult[i], 0.2);
        }
    }

    private float[][] getTrainingData() {
        return new float[][]{
                new float[]{0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0},
                new float[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                new float[]{0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new float[]{0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1},
                new float[]{0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0},
                new float[]{0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                new float[]{1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                new float[]{0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0},
                new float[]{1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0},
                new float[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                new float[]{0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1}
        };
    }

    private float[][] getTrainingResults() {
        return new float[][]{
                new float[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // "0"
                new float[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // "0"
                new float[]{0, 1, 0, 0, 0, 0, 0, 0, 0, 0},// "1"
                new float[]{0, 0, 1, 0, 0, 0, 0, 0, 0, 0},// "2"
                new float[]{0, 0, 0, 1, 0, 0, 0, 0, 0, 0},// "3"
                new float[]{0, 0, 0, 0, 1, 0, 0, 0, 0, 0},// "4"
                new float[]{0, 0, 0, 0, 0, 1, 0, 0, 0, 0},// "5"
                new float[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0},// "6"
                new float[]{0, 0, 0, 0, 0, 0, 0, 1, 0, 0},// "7"
                new float[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 0},// "8"
                new float[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1}// "9"
        };
    }

    private void train(float[][] trainingData, float[][] trainingResults) {
        for (int iteration = 0; iteration < NeuralNetworkConstants.ITERATIONS * 10; iteration++) {
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


    private void getDataSet() throws IOException {
        for (int i = 0; i < 10; i++) {
            reader.redImage(String.format("C:\\ocr\\%d.jpg", i));
        }
    }
}
