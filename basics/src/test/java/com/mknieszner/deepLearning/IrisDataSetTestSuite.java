package com.mknieszner.deepLearning;

import org.deeplearning4j.datasets.iterator.impl.IrisDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.api.Layer;
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
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.util.Random;

public class IrisDataSetTestSuite {

    @Test
    public void irisDataSetTest() {
        /*
         * training set :
         *
         * iris data set : 150 input set
         *
         * input:
         * x | y | z | u
         *
         * output:
         * (1,0,0)
         * (0,1,0)
         * (0,0,1)
         */

        int NUM_OF_INPUTS = 4;
        int NUM_OF_OUTPUTS = 3;
        int NUM_OF_SAMPLES = 150;
        int BATCH_SIZE = 150;
        int SPLIT_TRAIN_NUM = (int) (BATCH_SIZE * 0.8);
        int SEED = 123;

        DataSetIterator dataSetIterator = new IrisDataSetIterator(BATCH_SIZE, NUM_OF_SAMPLES);
        DataSet next = dataSetIterator.next();


        next.shuffle();
        next.normalize();

        SplitTestAndTrain testAndTrain = next.splitTestAndTrain(SPLIT_TRAIN_NUM, new Random(SEED));
        DataSet train = testAndTrain.getTrain();
        DataSet test = testAndTrain.getTest();


        NeuralNetConfiguration configuration = new NeuralNetConfiguration();
        configuration.setIterationCount(100000);

        NeuralNetConfiguration.Builder builder = new NeuralNetConfiguration.Builder(configuration);
        builder.updater(new Sgd(0.01));
        builder.seed(123);

        builder.optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT);
        builder.biasInit(0);
        builder.miniBatch(false);


        DenseLayer.Builder hiddenLayerBuilder = new DenseLayer.Builder();
        hiddenLayerBuilder.nIn(NUM_OF_INPUTS);
        hiddenLayerBuilder.nOut(5);
        hiddenLayerBuilder.activation(Activation.SIGMOID);
        hiddenLayerBuilder.weightInit(WeightInit.DISTRIBUTION);
        hiddenLayerBuilder.dist(new UniformDistribution(0, 1));

        DenseLayer.Builder hiddenLayerBuilder2 = new DenseLayer.Builder();
        hiddenLayerBuilder2.nIn(5);
        hiddenLayerBuilder2.nOut(5);
        hiddenLayerBuilder2.activation(Activation.SIGMOID);
        hiddenLayerBuilder2.weightInit(WeightInit.DISTRIBUTION);
        hiddenLayerBuilder2.dist(new UniformDistribution(0, 1));

        OutputLayer.Builder outputLayerBuilder = new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD);
        outputLayerBuilder.nIn(5);
        outputLayerBuilder.nOut(NUM_OF_OUTPUTS);
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

        for (int i = 0; i < 100000; i++) {
            neuralNetwork.fit(train);
        }

        Evaluation evaluation = new Evaluation(2);
        evaluation.eval(test.getLabels(), neuralNetwork.output(test.getFeatureMatrix(), Layer.TrainingMode.TEST));
        System.out.println(evaluation.stats());
    }
}

