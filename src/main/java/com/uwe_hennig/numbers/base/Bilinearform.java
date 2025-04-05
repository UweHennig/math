/**
 * @(#)Bilinearform.java
 * Copyright (c) 2023 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers.base;

/**
 * Bilinearform
 * @author Uwe Hennig
 */
public interface Bilinearform {
    double compute(Number[] vectorU, Number[] vectorV);
}
