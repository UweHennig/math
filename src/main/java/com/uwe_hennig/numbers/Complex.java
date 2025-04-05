/**
 * @(#)Complex.java
 * Copyright (c) 2023 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers;

import static com.uwe_hennig.numbers.base.Rules.useComplex;

import com.uwe_hennig.numbers.base.Number;

/**
 * Complex
 * @author Uwe Hennig
 */
public class Complex extends Number {
    static {
        useComplex();
    };

    public Complex() {
        super();
    }

    public Complex(double x) {
        super(x);
    }

    public Complex(double a, double ib) {
        super(a, ib);
    }

    @Override
    protected Number newInstance() {
        return new Complex();
    }
}
