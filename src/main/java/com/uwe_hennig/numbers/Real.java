/**
 * @(#)Real.java
 * Copyright (c) 2025 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers;

import com.uwe_hennig.numbers.base.AlgebraicNumber;
import com.uwe_hennig.numbers.base.RealContext;

/**
 * Real
 * @author Uwe Hennig
 */
public class Real extends AlgebraicNumber {
    public Real() {
        super(new RealContext());
    }
}
