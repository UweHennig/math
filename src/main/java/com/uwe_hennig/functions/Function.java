/**
 * @(#)Function.java
 * Copyright (c) 2020 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.functions;

/**
 * ISigmoidFunction
 * 
 * @author Uwe Hennig
 */
@FunctionalInterface
public interface Function<T> {
    T calc(T x, T x0, T minY, T maxY, T gradient);
}
