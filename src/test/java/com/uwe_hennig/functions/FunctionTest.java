/**
 * @(#)FunctionTest.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.functions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * FunctionTest
 * @author Uwe Hennig
 */
public class FunctionTest {

    @Test
    public void simpleFermiTest() {
        Function<Double> f = SigmoidFunctions::fermi;
        assertEquals("fermi(0) != 0.5", 0.5D, f.calc(0.0D, 0.0D, 0.0D, 1.0D, 1.0D), 0.0001D);
        
        for (double x = -1.0D; x < 1.0D; x = x + 0.2D) {
            Double y = f.calc(x, 0.0D, 0.0D, 1.0D, 1.0D);
            printSigmoid("fermi", x, y);
        }
    }
    
    public void printSigmoid(String functionName, Double x, Double y) {
        System.out.printf("\n%s(%1.1f)=%1.1f", functionName, x, y);
    }
}
