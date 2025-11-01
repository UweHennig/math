/**
 * @(#)Generic.java
 * Copyright (c) 2024 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers;

import static com.uwe_hennig.numbers.base.ScalarBase.R;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.uwe_hennig.numbers.base.AlgebraicContext;
import com.uwe_hennig.numbers.base.CayleyContext;
import com.uwe_hennig.numbers.base.ComplexContext;
import com.uwe_hennig.numbers.base.HamiltonContext;
import com.uwe_hennig.numbers.base.MathException;
import com.uwe_hennig.numbers.base.RealContext;
import com.uwe_hennig.numbers.base.ScalarBase;
import com.uwe_hennig.numbers.base.ScalarValue;
import com.uwe_hennig.numbers.base.SplitQuaternionContext;

/**
 * DivisionAlgebra
 * <pre>
 * This class combines different algebras:
 * Real, Complex, Hamilton, Split Quaternion, Cayley.
 * Every algebra has its own multiplication scheme and rules.
 *
 * Since this class only works for one multiplication scheme, a concrete scheme
 * must be selected before use. This can be achieved by calling AlgebraicNumber.complex().
 * At least 3 + 4i =  AlgebraicNumber.complex().r(3).i(4).create();
 * </pre>
 * @see AlgebraRules
 * @author Uwe Hennig
 */
public class AlgebraicNumber {
    private ScalarValue[] scalarValues = new ScalarValue[8];
    private final AlgebraicContext context;

    protected  AlgebraicNumber(AlgebraicContext context) {
        this.context = context;
        IntStream.range(0, 8).forEach(i -> scalarValues[i] = new ScalarValue(0.0, ScalarBase.of(i)));
    }

    public AlgebraicNumber(AlgebraicContext context, ScalarValue ... scalarValues) {
        this(context);
        this.scalarValues = scalarValues;
    }

    public static final class NumberBuilder {
        private final ScalarValue[] scalarValues = new ScalarValue[8];
        private EnumSet<ScalarBase> used = EnumSet.noneOf(ScalarBase.class);
        private AlgebraicContext context;

        private NumberBuilder(AlgebraicContext context) {
            this.context = context;
            IntStream.range(0, 8).forEach(i -> scalarValues[i] = new ScalarValue(0.0, ScalarBase.of(i)));
        }

        public NumberBuilder r(double value) { return set(value, ScalarBase.R);}
        public NumberBuilder i(double value) { return set(value, ScalarBase.I);}
        public NumberBuilder j(double value) { return set(value, ScalarBase.J);}
        public NumberBuilder k(double value) { return set(value, ScalarBase.K);}
        public NumberBuilder l(double value) { return set(value, ScalarBase.L);}
        public NumberBuilder m(double value) { return set(value, ScalarBase.M);}
        public NumberBuilder n(double value) { return set(value, ScalarBase.N);}
        public NumberBuilder o(double value) { return set(value, ScalarBase.O);}

        private NumberBuilder set(double value, ScalarBase base) {
            if (!used.add(base)) {
                throw new IllegalStateException("Component " + base + " already set.");
            }
            scalarValues[base.id()] = new ScalarValue(value, base);
            return this;
        }
        public AlgebraicNumber create() {
            return new AlgebraicNumber(context, this.scalarValues);
        }
    }

    public static NumberBuilder real() {
        return new NumberBuilder(RealContext.getInstance());
    }

    public static NumberBuilder complex() {
        return new NumberBuilder(ComplexContext.getInstance());
    }

    public static NumberBuilder hamilton() {
        return new NumberBuilder(HamiltonContext.getInstance());
    }

    public static NumberBuilder cayley() {
        return new NumberBuilder(CayleyContext.getInstance());
    }

    public static NumberBuilder splitQuaternion() {
        return new NumberBuilder(SplitQuaternionContext.getInstance());
    }

    protected double getValue(ScalarBase base) {
        checkBase(base);
        return getScalarValue(base).value();
    }

    protected ScalarValue getScalarValue(ScalarBase base) {
        return scalarValues[base.id()];
    }

    public boolean isZero() {
        return this.equals(new AlgebraicNumber(this.context, new ScalarValue(0, R)));
    }

    public boolean isOne() {
        AlgebraicNumber one = new AlgebraicNumber(this.context, new ScalarValue(1, R));
        return this.equals(one);
    }

    public AlgebraicNumber add(AlgebraicNumber summand) {
        AlgebraicNumber result = new AlgebraicNumber(this.context);

        for (ScalarBase base : context.getScalarBases()) {
            double x = this.getValue(base) + summand.getValue(base);
            result.scalarValues[base.id()] = new ScalarValue(x, base);
        }

        return result;
    }

    public AlgebraicNumber sub(AlgebraicNumber summand) {
        AlgebraicNumber result = new AlgebraicNumber(this.context);

        for (ScalarBase base : context.getScalarBases()) {
            ScalarValue thisBaseValue = getScalarValue(base);
            ScalarValue thatBaseValue = summand.getScalarValue(base);
            result.scalarValues[base.id()] = new ScalarValue(thisBaseValue.value() - thatBaseValue.value(), base);
        }

        return result;
    }

    public AlgebraicNumber multiply(AlgebraicNumber multiplicant) {
        AlgebraicNumber result = new AlgebraicNumber(this.context);

        for (ScalarBase srcBase : context.getScalarBases()) {
            ScalarValue srcValue = this.getScalarValue(srcBase);

            for (ScalarBase trgBase : context.getScalarBases()) {
                ScalarValue trgValue = multiplicant.getScalarValue(trgBase);
                ScalarValue product = context.mult(srcValue, trgValue);
                ScalarValue existing = result.getScalarValue(product.base());

                result.scalarValues[product.base().id()] = new ScalarValue(existing.value() + product.value(), product.base());
            }
        }

        return result;
    }

    public AlgebraicNumber division(AlgebraicNumber divisor) {
        AlgebraicNumber invers = divisor.invers();
        return this.multiply(invers);
    }

    public AlgebraicNumber multiply(double real) {
        AlgebraicNumber result = new AlgebraicNumber(this.context);
        for (ScalarBase base : context.getScalarBases()) {
            ScalarValue t = this.scalarValues[base.id()];
            ScalarValue o = new ScalarValue(real, R);
            result.scalarValues[base.id()] = context.mult(t, o);
        }
        return result;
    }

    public AlgebraicNumber conjugate() {
        AlgebraicNumber result = new AlgebraicNumber(this.context);
        for (ScalarBase base : context.getScalarBases()) {
            if (base.equals(R)) {
                result.scalarValues[0] =new ScalarValue(this.getScalarValue(base).value(), R);
            } else {
                result.scalarValues[base.id()] = new ScalarValue(-this.getScalarValue(base).value(), base);
            }
        }

        return result;
    }

    public double norm() {
        AlgebraicNumber conjugate = this.conjugate();
        AlgebraicNumber result = conjugate.multiply(this);
        double d = result.getScalarValue(R).value();
        if (d >= 0) {
            return Math.sqrt(d);
        } else {
            throw new MathException("Norm value negative!");
        }
    }

    public AlgebraicNumber normalize() {
        double norm = this.norm();
        return this.multiply(1 / norm);
    }

    public AlgebraicNumber invers() {
        if (isZero()) {
            throw new RuntimeException("Generic errro can't invers zero!");
        }
        double normsquare = Math.pow(this.norm(), 2);
        AlgebraicNumber result = this.conjugate();
        result = result.multiply(1.0D / normsquare);

        return result;
    }

    @Override
    public String toString() {
        return Stream.of(scalarValues)
            .filter(sv -> sv.value() != 0 || (sv.value() == 0 && sv.base().id() == 0))
            .map(sv -> {
                String formatted = format(Math.abs(sv.value()));
                String suffix = sv.base() == ScalarBase.R ? "" : sv.base().name().toLowerCase();
                return (sv.value() >= 0 ? " + " : " - ") + formatted + suffix;
            })
            .collect(Collectors.joining())
            .replaceFirst("^ \\+ ", "    ")
            .replaceFirst("^ - ", " - ")
            .replaceAll("  ", " ");
    }

    private static String format(double value) {
        double abs = Math.abs(value);
        if ((abs != 0.0 && (abs < 1e-3 || abs >= 1e4))) {
            return String.format("%5.6e", value);
        } else {
            return String.format("%5.2f", value);
        }
    }

    private int baseSize() {
        return context.getScalarBases().size();
    }

    private void checkBase(ScalarBase base) {
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
        if (obj == null || !(obj instanceof AlgebraicNumber that)) {
            return false;
        }
        return Arrays.equals(this.scalarValues, that.scalarValues);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(scalarValues);
    }
}
