/**
 * @(#)AlgebraicContext.java
 * Copyright (c) 2025 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers.base;

import java.util.List;

/**
 * AlgebraicContext
 * @author Uwe Hennig
 */
public interface AlgebraicContext {
    boolean isValidBase(ScalarBase base);
    List<ScalarBase> getScalarBases();

    ScalarValue mult(ScalarValue t, ScalarValue o);
    ScalarValue add(ScalarValue t, ScalarValue o);
    ScalarValue sub(ScalarValue t, ScalarValue o);
    ScalarValue negate(ScalarValue t);
}
