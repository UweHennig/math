/**
 * @(#)SigmoidFunctions.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.functions;

/**
 * SigmoidFunctions
 * 
 * @author Uwe Hennig
 */
public class SigmoidFunctions {
    /**
     * Jump : 0 if x<0 else 1
     * 
     * @param x         param x
     * @param x0        param x0
     * @param minY      param minY
     * @param maxY      param maxY
     * @param gradient  param gradient
     * @return
     */
    public static Double jump(Double x, Double x0, Double minY, Double maxY, Double gradient) {
        return x < x0 ? minY : maxY;
    }

    // Fermi : 1 + (1 + e^(-x)
    public static Double fermi(Double x, Double x0, Double minY, Double maxY, Double gradient) {
        return minY + (maxY - minY) / (1.0 + Math.exp(-gradient * (x - x0)));
    }

    // Sin : (sin(x) + 1)/2 for [-pi/2, pi/2]
    public static Double sin(Double x, Double x0, Double minY, Double maxY, Double gradient) {
        if (x < -Math.PI / 2.0D) {
            return minY;
        } else if (x > Math.PI / 2.0D) {
            return maxY;
        } else {
            return minY + (maxY - minY) * (Math.sin(gradient * (x - x0)) + 1.0) / 2.0;
        }
    }

    // Abs : ( ( x / (1 + abs(x)) + 1 )/2
    public static Double abs(Double x, Double x0, Double minY, Double maxY, Double gradient) {
        return ((gradient * (x - x0) / (1 + Math.abs(gradient * (x - x0))) + 1.0D) / 2.0D) * (maxY - minY) + minY;
    }

    // Tanh : tanh(x)/2 + 0.5
    public static Double tanh(Double x, Double x0, Double minY, Double maxY, Double gradient) {
        return minY + (maxY - minY) * Math.tanh(gradient * (x - x0)) / 2.0D + 0.5D;
    }

    // Linear: a * (x-x0) + b
    public static Double linear(Double x, Double x0, Double minY, Double maxY, Double gradient) {
        return Math.min(Math.max(minY + (maxY - minY) * gradient * (x - x0) + 0.5, minY), maxY);
    }

    // ID : X
    public static Double identity(Double x, Double x0, Double minY, Double maxY, Double gradient) {
        return x;
    }

    // Zero : 0.0
    public static Double zero(Double x, Double x0, Double minY, Double maxY, Double gradient) {
        return 0.0;
    }

    // Negative : -X
    public static Double negative(Double x, Double x0, Double minY, Double maxY, Double gradient) {
        return -x;
    }

}
