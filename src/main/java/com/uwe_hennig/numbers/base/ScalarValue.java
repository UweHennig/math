/**
 * @(#)ScalarValue.java
 * Copyright (c) 2024 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers.base;

import static com.uwe_hennig.numbers.base.ScalarBase.R;

import java.util.Objects;

/**
 * ScalarValue
 * @author Uwe Hennig
 */
public final record ScalarValue(double value, ScalarBase base) {
    private static final double EPSILON = 1e-9;

    public boolean isZero() {
        return Math.abs(this.value) < EPSILON;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(base, value);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ScalarValue that)) {
            return false;
        }
        return base == that.base && Math.abs(this.value - that.value) < EPSILON;
    }

    @Override
    public final String toString() {
        final String format = "  %1s %6.2f %s";
        if (R.equals(base)) {
            return String.format(format, value <0? "-" : " " , Math.abs(value), "");
        }
        return String.format(format, value <0? "-" : "+" , Math.abs(value), base.toString());
    }
}
