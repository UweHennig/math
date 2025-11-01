/**
 * @(#)CayleyContext.java
 * Copyright (c) 2025 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers.base;

import static com.uwe_hennig.numbers.base.ScalarBase.R;
import static com.uwe_hennig.numbers.base.ScalarBase.I;
import static com.uwe_hennig.numbers.base.ScalarBase.J;
import static com.uwe_hennig.numbers.base.ScalarBase.K;
import static com.uwe_hennig.numbers.base.ScalarBase.L;
import static com.uwe_hennig.numbers.base.ScalarBase.M;
import static com.uwe_hennig.numbers.base.ScalarBase.N;
import static com.uwe_hennig.numbers.base.ScalarBase.O;

import java.util.List;


/**
 * CayleyContext
 * @author Uwe Hennig
 */
public class CayleyContext extends AbstractAlgebraicContext {
    private static final CayleyContext INSTANCE = new CayleyContext();

    CayleyContext() {
        addRule(R, R, (x, y) -> new ScalarValue(x * y, R));
        addRule(R, I, (x, y) -> new ScalarValue(x * y, I));
        addRule(R, J, (x, y) -> new ScalarValue(x * y, J));
        addRule(R, K, (x, y) -> new ScalarValue(x * y, K));
        addRule(R, L, (x, y) -> new ScalarValue(x * y, L));
        addRule(R, M, (x, y) -> new ScalarValue(x * y, M));
        addRule(R, N, (x, y) -> new ScalarValue(x * y, N));
        addRule(R, O, (x, y) -> new ScalarValue(x * y, O));

        addRule(I, R, (x, y) -> new ScalarValue( x * y, I));
        addRule(I, I, (x, y) -> new ScalarValue(-x * y, R));
        addRule(I, J, (x, y) -> new ScalarValue( x * y, K));
        addRule(I, K, (x, y) -> new ScalarValue(-x * y, J));
        addRule(I, L, (x, y) -> new ScalarValue( x * y, M));
        addRule(I, M, (x, y) -> new ScalarValue(-x * y, L));
        addRule(I, N, (x, y) -> new ScalarValue(-x * y, O));
        addRule(I, O, (x, y) -> new ScalarValue( x * y, N));

        addRule(J, R, (x, y) -> new ScalarValue( x * y, J));
        addRule(J, I, (x, y) -> new ScalarValue(-x * y, K));
        addRule(J, J, (x, y) -> new ScalarValue(-x * y, R));
        addRule(J, K, (x, y) -> new ScalarValue( x * y, I));
        addRule(J, L, (x, y) -> new ScalarValue( x * y, N));
        addRule(J, M, (x, y) -> new ScalarValue( x * y, O));
        addRule(J, N, (x, y) -> new ScalarValue(-x * y, L));
        addRule(J, O, (x, y) -> new ScalarValue(-x * y, M));

        addRule(K, R, (x, y) -> new ScalarValue( x * y, K));
        addRule(K, I, (x, y) -> new ScalarValue( x * y, J));
        addRule(K, J, (x, y) -> new ScalarValue(-x * y, I));
        addRule(K, K, (x, y) -> new ScalarValue(-x * y, R));
        addRule(K, L, (x, y) -> new ScalarValue( x * y, O));
        addRule(K, M, (x, y) -> new ScalarValue(-x * y, N));
        addRule(K, N, (x, y) -> new ScalarValue( x * y, M));
        addRule(K, O, (x, y) -> new ScalarValue(-x * y, L));

        addRule(L, R, (x, y) -> new ScalarValue( x * y, L));
        addRule(L, I, (x, y) -> new ScalarValue(-x * y, M));
        addRule(L, J, (x, y) -> new ScalarValue(-x * y, N));
        addRule(L, K, (x, y) -> new ScalarValue(-x * y, O));
        addRule(L, L, (x, y) -> new ScalarValue(-x * y, R));
        addRule(L, M, (x, y) -> new ScalarValue( x * y, I));
        addRule(L, N, (x, y) -> new ScalarValue( x * y, J));
        addRule(L, O, (x, y) -> new ScalarValue( x * y, K));

        addRule(M, R, (x, y) -> new ScalarValue( x * y, M));
        addRule(M, I, (x, y) -> new ScalarValue( x * y, L));
        addRule(M, J, (x, y) -> new ScalarValue(-x * y, O));
        addRule(M, K, (x, y) -> new ScalarValue( x * y, N));
        addRule(M, L, (x, y) -> new ScalarValue(-x * y, I));
        addRule(M, M, (x, y) -> new ScalarValue(-x * y, R));
        addRule(M, N, (x, y) -> new ScalarValue(-x * y, K));
        addRule(M, O, (x, y) -> new ScalarValue( x * y, J));

        addRule(N, R, (x, y) -> new ScalarValue( x * y, N));
        addRule(N, I, (x, y) -> new ScalarValue( x * y, O));
        addRule(N, J, (x, y) -> new ScalarValue( x * y, L));
        addRule(N, K, (x, y) -> new ScalarValue(-x * y, M));
        addRule(N, L, (x, y) -> new ScalarValue(-x * y, J));
        addRule(N, M, (x, y) -> new ScalarValue( x * y, K));
        addRule(N, N, (x, y) -> new ScalarValue(-x * y, R));
        addRule(N, O, (x, y) -> new ScalarValue(-x * y, I));

        addRule(O, R, (x, y) -> new ScalarValue( x * y, O));
        addRule(O, I, (x, y) -> new ScalarValue(-x * y, N));
        addRule(O, J, (x, y) -> new ScalarValue( x * y, M));
        addRule(O, K, (x, y) -> new ScalarValue( x * y, L));
        addRule(O, L, (x, y) -> new ScalarValue(-x * y, K));
        addRule(O, M, (x, y) -> new ScalarValue(-x * y, J));
        addRule(O, N, (x, y) -> new ScalarValue( x * y, I));
        addRule(O, O, (x, y) -> new ScalarValue(-x * y, R));
    }

    public static CayleyContext getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValidBase(ScalarBase base) {
        return base.id() <= ScalarBase.O.id();
    }

    @Override
    public List<ScalarBase> getScalarBases() {
        return List.of(R, I, J, K, L, M, N, O);
    }

}
