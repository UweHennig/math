/**
  * @(#)ScalarBase.java
 * Copyright (c) 2024 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers.base;

/**
 * ScalarBase
 * @author Uwe Hennig
 */
public enum ScalarBase {
    R(0), I(1), J(2), K(3), L(4), M(5), N(6), O(7);

    private static final ScalarBase[] VALUES = values();
    private final int base;

    private ScalarBase(int base) {
        this.base = base;
    }

    public int id() {
        return base;
    }

    public static ScalarBase of(int i) {
        return VALUES[i];
    }
}
