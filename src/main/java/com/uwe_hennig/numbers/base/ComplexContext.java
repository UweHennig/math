/**
 * @(#)ComplexContext.java
 * Copyright (c) 2025 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers.base;

import static com.uwe_hennig.numbers.base.ScalarBase.R;
import static com.uwe_hennig.numbers.base.ScalarBase.I;

import java.util.List;


/**
 * ComplexContext
 * @author Uwe Hennig
 */
public class ComplexContext extends AbstractAlgebraicContext {
    private static final ComplexContext INSTANCE = new ComplexContext();

    public ComplexContext() {
        addRule(R, R, (x, y) -> new ScalarValue( x * y, R));
        addRule(R, I, (x, y) -> new ScalarValue( x * y, I));
        addRule(I, R, (x, y) -> new ScalarValue( x * y, I));
        addRule(I, I, (x, y) -> new ScalarValue(-x * y, R));
    }

    public static ComplexContext getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValidBase(ScalarBase base) {
        return base.id() <= ScalarBase.I.id();
    }

    @Override
    public List<ScalarBase> getScalarBases() {
        return List.of(R, I);
    }
}
