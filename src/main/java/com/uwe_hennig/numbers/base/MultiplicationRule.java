/**
 * @(#)MultiplicationRule.java
 * Copyright (c) 2024 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers.base;

/**
 * MultiplicationRule
 * @author Uwe Hennig
 */
public interface MultiplicationRule {
    ScalarValue mult(Double x, Double y);
}
