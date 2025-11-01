/**
 * @(#)AbstractAlgebraicContext.java
 * Copyright (c) 2025 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers.base;

import java.util.EnumMap;
import java.util.Map;

/**
 * AbstractAlgebraicContext
 * @author Uwe Hennig
 */
public abstract class AbstractAlgebraicContext implements AlgebraicContext{
    protected Map<ScalarBase, Map<ScalarBase, MultiplicationRule>> rules = new EnumMap<>(ScalarBase.class);

    @Override
    public ScalarValue mult(ScalarValue t, ScalarValue o) {
        return rule(t.base(), o.base()).mult(t.value(), o.value());
    }

    @Override
    public ScalarValue add(ScalarValue t, ScalarValue o) {
        if (t.base().equals(o.base())) {
            return new ScalarValue(t.value() + o.value(), t.base());
        }
        throw new MathException("Invalid base for add!");
    }

    @Override
    public ScalarValue sub(ScalarValue t, ScalarValue o) {
        if (t.base().equals(o.base())) {
            return new ScalarValue(t.value() - o.value(), t.base());
        }
        throw new MathException("Invalid base for sub!");
    }

    @Override
    public ScalarValue negate(ScalarValue t) {
        return new ScalarValue(-t.value(), t.base());
    }

    protected void addRule(ScalarBase d1, ScalarBase d2, MultiplicationRule rule) {
        rules.putIfAbsent(d1, new EnumMap<>(ScalarBase.class));
        if (rules.get(d1).get(d2) == null) {
            rules.get(d1).put(d2, rule);
        }
    }

    protected MultiplicationRule rule(ScalarBase d1, ScalarBase d2) {
        Map<ScalarBase, MultiplicationRule> inner = rules.get(d1);

        if (inner != null) {
            MultiplicationRule rule = inner.get(d2);
            if (rule != null) {
                return rule;
            }
        }
        throw new MathException("No multiplication rule found!");
    }
}
