/**
 * @(#)Real.java
 * Copyright (c) 2023 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers;

import static com.uwe_hennig.numbers.base.Rules.useReal;

import com.uwe_hennig.numbers.base.Number;

/**
 * Real
 * @author Uwe Hennig
 */
public class Real extends Number {
    static {
        useReal();
    };

    public Real() {
        super();
    }

    public Real(double x) {
        super(x);
    }

    @Override
    protected Number newInstance() {
        return new Real();
    }
}
