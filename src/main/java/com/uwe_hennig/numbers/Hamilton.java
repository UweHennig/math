/**
 * @(#)Hamilton.java
 * Copyright (c) 2023 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers;

import static com.uwe_hennig.numbers.base.Rules.useHamilton;

import java.security.InvalidParameterException;

import com.uwe_hennig.numbers.base.Number;

/**
 * Hamilton
 * @author Uwe Hennig
 */
public class Hamilton extends Number {
    static {
        useHamilton();
    };

    public Hamilton() {
        super();
    }

    public Hamilton(double ... values) {
        super(values);
    }

    @Override
    protected Number newInstance() {
        return new Hamilton();
    }
}
