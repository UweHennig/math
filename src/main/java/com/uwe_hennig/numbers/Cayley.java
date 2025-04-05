/**
 * @(#)Cayley.java
 * Copyright (c) 2023 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers;

import static com.uwe_hennig.numbers.base.Rules.useCayley;

import com.uwe_hennig.numbers.base.Number;

/**
 * Cayley
 * @author Uwe Hennig
 */
public class Cayley extends Number {
    static {
        useCayley();
    };

    public Cayley() {
        super();
    }

    public Cayley(double ... values) {
        super(values);
    }

    @Override
    protected Number newInstance() {
        return new Cayley();
    }

}
