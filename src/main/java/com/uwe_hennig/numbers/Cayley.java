/**
 * @(#)Cayley.java
 * Copyright (c) 2025 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers;

import com.uwe_hennig.numbers.base.AlgebraicNumber;
import com.uwe_hennig.numbers.base.CayleyContext;

/**
 * Cayley
 * @author Uwe Hennig
 */
public class Cayley extends AlgebraicNumber {
    public Cayley() {
        super(CayleyContext.getInstance());
    }
}
