/**
 * @(#)Complex.java
 * Copyright (c) 2025 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers;

import com.uwe_hennig.numbers.base.AlgebraicNumber;
import com.uwe_hennig.numbers.base.ComplexContext;

/**
 * Complex
 * @author Uwe Hennig
 */
public class Complex extends AlgebraicNumber {
    public Complex() {
        super(ComplexContext.getInstance());
    }
}
