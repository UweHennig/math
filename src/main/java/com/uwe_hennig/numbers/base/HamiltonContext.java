/**
 * @(#)HamiltonContext.java
 * Copyright (c) 2025 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers.base;

import static com.uwe_hennig.numbers.base.ScalarBase.R;
import static com.uwe_hennig.numbers.base.ScalarBase.I;
import static com.uwe_hennig.numbers.base.ScalarBase.J;
import static com.uwe_hennig.numbers.base.ScalarBase.K;

import java.util.EnumMap;
import java.util.List;

/**
 * HamiltonContext
 * @author Uwe Hennig
 */
public class HamiltonContext extends AbstractAlgebraicContext {
    private static final HamiltonContext INSTANCE = new HamiltonContext();

    public HamiltonContext() {
        rules = new EnumMap<>(ScalarBase.class);
        addRule(R, R, (x, y) -> new ScalarValue(x * y, R));
        addRule(R, I, (x, y) -> new ScalarValue(x * y, I));
        addRule(R, J, (x, y) -> new ScalarValue(x * y, J));
        addRule(R, K, (x, y) -> new ScalarValue(x * y, K));

        addRule(I, R, (x, y) -> new ScalarValue( x * y, I));
        addRule(I, I, (x, y) -> new ScalarValue(-x * y, R));
        addRule(I, J, (x, y) -> new ScalarValue( x * y, K));
        addRule(I, K, (x, y) -> new ScalarValue(-x * y, J));

        addRule(J, R, (x, y) -> new ScalarValue( x * y, J));
        addRule(J, I, (x, y) -> new ScalarValue(-x * y, K));
        addRule(J, J, (x, y) -> new ScalarValue(-x * y, R));
        addRule(J, K, (x, y) -> new ScalarValue( x * y, I));

        addRule(K, R, (x, y) -> new ScalarValue( x * y, K));
        addRule(K, I, (x, y) -> new ScalarValue( x * y, J));
        addRule(K, J, (x, y) -> new ScalarValue(-x * y, I));
        addRule(K, K, (x, y) -> new ScalarValue(-x * y, R));
    }

    public static HamiltonContext getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValidBase(ScalarBase base) {
        return base.id() <= ScalarBase.K.id();
    }

    @Override
    public List<ScalarBase> getScalarBases() {
        return List.of(R, I, J, K);
    }

}
