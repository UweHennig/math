/**
 * @(#)Hamilton.java
 * Copyright (c) 2025 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers;

import com.uwe_hennig.numbers.base.AlgebraicNumber;
import com.uwe_hennig.numbers.base.HamiltonContext;

/**
 * Hamilton
 * @author Uwe Hennig
 */
public class Hamilton extends AlgebraicNumber {
    protected Hamilton() {
        super(HamiltonContext.getInstance());
    }
}
