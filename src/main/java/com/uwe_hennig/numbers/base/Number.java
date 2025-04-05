/**
 * @(#)Number.java
 * Copyright (c) 2023 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers.base;

import static com.uwe_hennig.numbers.base.Base.R;
import static com.uwe_hennig.numbers.base.Rules.getBases;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Locale;

/**
 * Number
 *
 * @author Uwe Hennig
 */
public class Number {
    protected Value[] scalarValues = new Value[8];

    protected Number() {
        this(0.0D);
    }

    protected Number(double... values) {
        int max = getBases().size();

        if (values.length > max) {
            throw new InvalidParameterException("Generic c'tor max parameter greater thant max scalar size!");
        }

        for (Base base : Base.values()) {
            int i = base.id();
            if (i < max) {
                if (i < values.length) {
                    scalarValues[i] = new Value(base, values[i]);
                } else {
                    scalarValues[i] = new Value(base, 0.0D);
                }
            } else {
                scalarValues[i] = new Value(base, 0.0D);
            }
        }
    }

    protected Number(Base base, double value) {
        checkBase(base);
        for (Base b : Base.values()) {
            if (b.equals(base)) {
                scalarValues[b.id()] = new Value(base, value);
            } else {
                scalarValues[b.id()] = new Value(base, 0.0D);
            }
        }
    }

    protected Number(Number other) {
        for (Base base : Base.values()) {
            setScalarValue(base, other.getScalarValue(base));
        }
    }

    // immutable with deep copy
    public Number with(Base base, double value) {
        checkBase(base);
        Number number = newInstance();
        for (Base b : getBases()) {
            if (!b.equals(base)) {
                number.setValue(b, this.getValue(base));
            } else {
                number.setValue(base, value);
            }
        }
        return number;
    }

    protected double getValue(Base base) {
        checkBase(base);
        return getScalarValue(base).value();
    }

    // mutable
    protected void setValue(Base base, double value) {
        checkBase(base);
        setScalarValue(base, new Value(base, value));
    }

    private Value getScalarValue(Base base) {
        return scalarValues[base.id()];
    }

    private void setScalarValue(Base base, Value value) {
        scalarValues[base.id()] = value;
    }

    public boolean isZero() {
        return getBases().stream().allMatch(d -> getScalarValue(d).value() == 0.0);
    }

    public boolean isReal() {
        return getBases().stream().skip(1).allMatch(d -> getScalarValue(d).value() == 0.0);
    }

    public Number add(Number summand) {
        Number result = newInstance();

        for (Base base : getBases()) {
            double x = this.getValue(base) + summand.getValue(base);
            result.setValue(base, x);
        }

        return result;
    }

    public Number sub(Number summand) {
        Number result = newInstance();

        for (Base base : getBases()) {
            Value thisBaseValue = getScalarValue(base);
            Value thatBaseValue = summand.getScalarValue(base);
            result.scalarValues[base.id()] = new Value(base, thisBaseValue.value() - thatBaseValue.value());
        }

        return result;
    }

    public Number multiply(Number multiplicant) {
        Number result = newInstance();

        for (Base srcBase : getBases()) {
            Value srcValue = this.getScalarValue(srcBase);

            for (Base trgBase : getBases()) {
                Value trgValue = multiplicant.getScalarValue(trgBase);
                Value product = srcValue.mult(trgValue);
                Value existing = result.getScalarValue(product.base());

                result.scalarValues[product.base().id()] = new Value(product.base(), existing.value() + product.value());
            }
        }

        return result;
    }

    public Number division(Number divisor) {
        Number invers = divisor.invers();
        return this.multiply(invers);
    }

    public Number multiply(double real) {
        Number result = newInstance();
        for (Base base : getBases()) {
            result.scalarValues[base.id()] = this.scalarValues[base.id()].mult(new Value(R, real));
        }
        return result;
    }

    public Number conjugate() {
        Number result = newInstance();
        for (Base base : getBases()) {
            if (base.equals(R)) {
                result.scalarValues[0] = Value.rValue(this.getScalarValue(base).value());
            } else {
                result.scalarValues[base.id()] = new Value(base, -this.getScalarValue(base).value());
            }
        }

        return result;
    }

    public double norm() {
        Number conjugate = this.conjugate();
        Number result = conjugate.multiply(this);
        double d = result.getScalarValue(R).value();
        if (d >= 0) {
            return Math.sqrt(d);
        } else {
            throw new RuntimeException("Norm value negative!");
        }
    }

    public Number normalize() {
        double norm = this.norm();
        return this.multiply(1 / norm);
    }

    public Number invers() {
        if (isZero()) {
            throw new RuntimeException("Generic errro can't invers zero!");
        }
        double normsquare = Math.pow(this.norm(), 2);
        Number result = this.conjugate();
        result = result.multiply(1.0D / normsquare);

        return result;
    }

    @Override
    public String toString() {
        final String formatR = "%1s%-5.2f";
        final String formatX = "%1s%5.2f%s";

        if (isZero()) {
            return String.format(Locale.ENGLISH, formatR, " ", 0D);
        }

        if (isReal()) {
            return String.format(Locale.ENGLISH, formatR, " ", 1D);
        }

        StringBuilder builder = new StringBuilder();

        for (Base base : getBases()) {
            Value current = getScalarValue(base);
            if (base.equals(R)) {
                builder.append(String.format(Locale.ENGLISH, formatR, current.value() >= 0 ? " " : "-", Math.abs(current.value())));
            } else {
                if (current.value() != 0.0) {
                    builder.append(String.format(Locale.ENGLISH, formatX, current.value() >= 0 ? " + " : " - ", Math.abs(current.value()),
                        current.base().toString().toLowerCase()));
                }
            }
        }

        return builder.toString();
    }

    private static int baseSize() {
        return getBases().size();
    }

    private static void checkBase(Base base) {
        int max = baseSize();
        if (base.id() > max) {
            throw new InvalidParameterException("Unsupported base!");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Number that)) {
            return false;
        }
        return Arrays.equals(this.scalarValues, that.scalarValues);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(scalarValues);
    }

    protected Number newInstance() {
        return new Number();
    }
}
