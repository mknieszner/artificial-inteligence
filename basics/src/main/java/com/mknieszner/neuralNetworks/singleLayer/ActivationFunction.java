package com.mknieszner.neuralNetworks.singleLayer;

public class ActivationFunction {
    public static int stepFunction(float activation){
        if(activation >= 1){
            return 1;
        }
        return 0;
    }
}
