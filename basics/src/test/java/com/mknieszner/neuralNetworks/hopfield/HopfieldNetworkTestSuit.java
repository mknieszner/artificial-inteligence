package com.mknieszner.neuralNetworks.hopfield;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class HopfieldNetworkTestSuit {

    @Test
    public void hopfieldNetworkTest() {
        HopfieldNetwork hopfieldNetwork = new HopfieldNetwork(4);
        double[][] weights;

        weights = hopfieldNetwork.train(new double[]{0, 1, 0, 1});
        System.out.println(Arrays.deepToString(weights));

        weights = hopfieldNetwork.train(new double[]{1, 1, 1, 1});
        System.out.println(Arrays.deepToString(weights));

        weights = hopfieldNetwork.train(new double[]{1, 0, 0, 1});
        System.out.println(Arrays.deepToString(weights));

        assertTrue(hopfieldNetwork.recall(new double[]{0, 1, 0, 1}));
        assertTrue(hopfieldNetwork.recall(new double[]{1, 1, 1, 1}));
        assertFalse(hopfieldNetwork.recall(new double[]{1, 0, 1, 1}));
        assertFalse(hopfieldNetwork.recall(new double[]{1, 1, 0, 0}));
    }
}
