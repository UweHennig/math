/**
 * @(#)RealContext.java
 * Copyright (c) 2025 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers.base;

import static com.uwe_hennig.numbers.base.ScalarBase.R;

import java.util.List;


/**
 * RealContext
 * @author Uwe Hennig
 */
public class RealContext extends AbstractAlgebraicContext {
    private static final RealContext INSTANCE = new RealContext();

    public RealContext() {
        addRule(R, R, (x, y) -> new ScalarValue(x * y, R));
    }

    public static RealContext getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValidBase(ScalarBase base) {
        return base.id() <= ScalarBase.R.id();
    }

    @Override
    public List<ScalarBase> getScalarBases() {
        return List.of(R);
    }
}
