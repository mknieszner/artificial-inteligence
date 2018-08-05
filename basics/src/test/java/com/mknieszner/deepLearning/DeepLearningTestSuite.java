package com.mknieszner.deepLearning;

import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.distribution.UniformDistribution;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.junit.Test;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;

public class DeepLearningTestSuite {

    @Test
    public void deepNeuralNetworkXORTest() {
        /*
         * training set :
         *
         *  input       |   labels
         *
         *  x   |   y   |   binary representation -> xORy
         *  0   |   0   |   (1,0) -> 0
         *  0   |   1   |   (0,1) -> 1
         *  1   |   0   |   (0,1) -> 1
         *  1   |   1   |   (1,0) -> 0
         */
        INDArray input = getXORInput();
        INDArray labels = getXORLabels();

        DataSet dataset = new DataSet(input, labels);

        NeuralNetConfiguration configuration = new NeuralNetConfiguration();
        configuration.setIterationCount(10000);

        NeuralNetConfiguration.Builder builder = new NeuralNetConfiguration.Builder(configuration);
        //learning rate - one of hyperparameters -> how fast will network learn and how accurate result will be
        //weight wj = wj - alpha(dL(w)/dwj), alpha is learning rate
        builder.updater(new Sgd(0.1));
        builder.seed(123);

        //used to find approximation of global minimum fe. with loss function
        builder.optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT);
        builder.biasInit(0);
        builder.miniBatch(false);

        //FIRST HIDDEN LAYER
        DenseLayer.Builder hiddenLayerBuilder = new DenseLayer.Builder();
        hiddenLayerBuilder.nIn(2);
        hiddenLayerBuilder.nOut(4);
        //activation function is used to make neural network non linear
        //RELU -> f(x)=max(0,x) because the gradient is either zero or constant so it can solve vanishing gradient issue
        hiddenLayerBuilder.activation(Activation.SOFTMAX);
        //random initialization of edge weights
        hiddenLayerBuilder.weightInit(WeightInit.DISTRIBUTION);
        hiddenLayerBuilder.dist(new UniformDistribution(0, 1));

        //SECOND HIDDEN LAYER
        DenseLayer.Builder hiddenLayerBuilder2 = new DenseLayer.Builder();
        hiddenLayerBuilder2.nIn(4);
        hiddenLayerBuilder2.nOut(4);
        hiddenLayerBuilder2.activation(Activation.SOFTMAX);
        hiddenLayerBuilder2.weightInit(WeightInit.DISTRIBUTION);
        hiddenLayerBuilder2.dist(new UniformDistribution(0, 1));

        //OUTPUT LAYER
        //Loss function measures how close the neural network is to ideal toward which it is training
        OutputLayer.Builder outputLayerBuilder = new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD);
        outputLayerBuilder.nIn(4);
        outputLayerBuilder.nOut(2);
        //softmax is used to classify output f(z)=e^z/Sum(e^z))
        outputLayerBuilder.activation(Activation.SOFTMAX);
        outputLayerBuilder.weightInit(WeightInit.DISTRIBUTION);
        outputLayerBuilder.dist(new UniformDistribution(0, 1));

        NeuralNetConfiguration.ListBuilder listBuilder = builder.list();

        listBuilder.layer(0, hiddenLayerBuilder.build());
        listBuilder.layer(1, hiddenLayerBuilder2.build());
        listBuilder.layer(2, outputLayerBuilder.build());
        listBuilder.pretrain(false);

        listBuilder.backprop(true);

        MultiLayerConfiguration networkConfiguration = listBuilder.build();
        MultiLayerNetwork neuralNetwork = new MultiLayerNetwork(networkConfiguration);
        neuralNetwork.init();

        neuralNetwork.setListeners(new ScoreIterationListener(100));

        for (int i = 0; i < 10000; i++) {
            neuralNetwork.fit(dataset);
        }

        INDArray output = neuralNetwork.output(dataset.getFeatureMatrix());

        Evaluation evaluation = new Evaluation(2);
        evaluation.eval(dataset.getLabels(), output);
        System.out.println(evaluation.stats());

        System.out.println("NEW PREDICTION");
        System.out.println(neuralNetwork.output(input));
    }

    private INDArray getXORInput() {
        INDArray input = Nd4j.zeros(4, 2);

        //1 row
        input.putScalar(new int[]{0, 0}, 0);
        input.putScalar(new int[]{0, 1}, 0);
        //2 row
        input.putScalar(new int[]{1, 0}, 1);
        input.putScalar(new int[]{1, 1}, 0);
        //3 row
        input.putScalar(new int[]{2, 0}, 0);
        input.putScalar(new int[]{2, 1}, 1);
        //4 row
        input.putScalar(new int[]{3, 0}, 1);
        input.putScalar(new int[]{3, 1}, 1);

        return input;
    }

    private INDArray getXORLabels() {
        INDArray labels = Nd4j.zeros(4, 2);

        //1 row
        labels.putScalar(new int[]{0, 0}, 1);
        labels.putScalar(new int[]{0, 1}, 0);
        //2 row
        labels.putScalar(new int[]{1, 0}, 0);
        labels.putScalar(new int[]{1, 1}, 1);
        //3 row
        labels.putScalar(new int[]{2, 0}, 0);
        labels.putScalar(new int[]{2, 1}, 1);
        //4 row
        labels.putScalar(new int[]{3, 0}, 1);
        labels.putScalar(new int[]{3, 1}, 0);

        return labels;
    }
}
