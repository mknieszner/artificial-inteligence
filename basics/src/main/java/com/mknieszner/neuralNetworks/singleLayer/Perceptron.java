package com.mknieszner.neuralNetworks.singleLayer;

public class Perceptron {
    private float[] weights;
    private float[][] input;
    private float[] output;
    private int numOfWeights;

    public Perceptron(float[][] input, float[] output) {
        this.input = input;
        this.output = output;
        this.numOfWeights = input[0].length;
        this.weights = new float[numOfWeights];

        initializeWeights();
    }

    private void initializeWeights() {
        for (int i = 0; i < numOfWeights; ++i) {
            weights[i] = 0;
        }
    }

    public void train(float learningRate) {
        float totalError = 1;
        while (totalError != 0) {
            totalError = 0;
            for (int i = 0; i < output.length; i++) {
                float calculatedOutput = calculateOutput(input[i]);
                float error = Math.abs(output[i] - calculatedOutput);

                totalError = totalError + error;

                for (int j = 0; j < numOfWeights; j++) {
                    weights[j] = weights[j] + learningRate * input[i][j] * error;
                }
            }
        }
    }

    public float calculateOutput(float[] input) {
        float sum = 0f;
        for (int i = 0; i < input.length; i++) {
            sum = sum + weights[i] * input[i];
        }
        return ActivationFunction.stepFunction(sum);
    }
}
