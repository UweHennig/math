/**
 * @(#)Base.java
 * Copyright (c) 2023 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers.base;

/**
 * Base
 * @author Uwe Hennig
 */
public enum Base {
    R(0), I(1), J(2), K(3), L(4), M(5), N(6), O(7);

    private final int base;

    private Base(int base) {
        this.base = base;
    }

    public int id() {
        return base;
    }
}
