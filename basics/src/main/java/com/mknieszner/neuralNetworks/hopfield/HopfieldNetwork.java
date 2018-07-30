package com.mknieszner.neuralNetworks.hopfield;

public class HopfieldNetwork {

    private double[][] weightMatrix;

    public HopfieldNetwork(int dimensions) {
        this.weightMatrix = new double[dimensions][dimensions];
    }

    public double[][] train(double[] pattern) {
        double[] patternBipolar = Utils.transform(pattern);
        double[][] patternMatrix = Matrix.outerProduct(patternBipolar);
        patternMatrix = Matrix.clearDiagonal(patternMatrix);
        this.weightMatrix = Matrix.addMatrix(this.weightMatrix, patternMatrix);
        return this.weightMatrix;
    }

    public boolean recall(double[] pattern) {
        double[] patternBipolar = Utils.transform(pattern);

        double[] result = Matrix.matrixVectorMultiplication(this.weightMatrix, patternBipolar);

        for (int i = 0; i < patternBipolar.length; ++i) {
            result[i] = ActivationFunction.stepFunction(result[i]);
        }

        for (int i = 0; i < patternBipolar.length; ++i) {
            if (patternBipolar[i] != result[i]) {
                return false;

            }
        }
        return true;
    }
}
