/**
 * @(#)Value.java
 * Copyright (c) 2023 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers.base;

import static com.uwe_hennig.numbers.base.Base.I;
import static com.uwe_hennig.numbers.base.Base.J;
import static com.uwe_hennig.numbers.base.Base.K;
import static com.uwe_hennig.numbers.base.Base.L;
import static com.uwe_hennig.numbers.base.Base.M;
import static com.uwe_hennig.numbers.base.Base.N;
import static com.uwe_hennig.numbers.base.Base.O;
import static com.uwe_hennig.numbers.base.Base.R;

import java.util.Objects;

/**
 * Value
 * @author Uwe Hennig
 */
public record Value(Base base, double value) {
    private static final double EPSILON = 1e-9;

    public Value withValue(double value) {
        return new Value(this.base, value);
    }

    public Value mult(Value x) {
        Mult rules = Rules.rule(this.base, x.base);
        if (rules != null) {
            return Rules.rule(this.base, x.base).mult(value, x.value);
        }
        return new Value(this.base, 0.0D);
    }

    public Value add(Value x) {
        if (base.equals(x.base)) {
            return withValue(this.value + x.value);
        }
        return this;
    }

    public Value sub(Value x) {
        if (base.equals(x.base)) {
            return withValue(this.value - x.value);
        }
        return this;
    }

    public Value negate() {
        return withValue(-this.value);
    }

    public static Value rValue(double value) {
        return new Value (R, value);
    }
    public static Value iValue(double value) {
        return new Value (I, value);
    }
    public static Value jValue(double value) {
        return new Value (J, value);
    }
    public static Value kValue(double value) {
        return new Value (K, value);
    }
    public static Value lValue(double value) {
        return new Value (L, value);
    }
    public static Value mValue(double value) {
        return new Value (M, value);
    }
    public static Value nValue(double value) {
        return new Value (N, value);
    }
    public static Value oValue(double value) {
        return new Value (O, value);
    }

    public boolean isZero() {
        return Math.abs(this.value) < EPSILON;
    }

    @Override
    public final String toString() {
        final String format = "  %1s %6.2f %s";
        if (R.equals(base)) {
            return String.format(format, value <0? "-" : " " , Math.abs(value), "");
        }
        return String.format(format, value <0? "-" : "+" , Math.abs(value), base.toString());
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
        if (obj == null || !(obj instanceof Value that)) {
            return false;
        }
        return base == that.base && Math.abs(this.value - that.value) < EPSILON;
    }

}
