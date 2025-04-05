/**
 * @(#)Rules.java
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Rules
 *
 * @author Uwe Hennig
 */
public class Rules {
    private static Map<Base, Map<Base, Mult>> rules = new HashMap<>();
    private static List<Base>                  Bases;
    private static Bilinearform                bilinearform;

    public static Mult rule(Base d1, Base d2) {
        if (rules.get(d1) != null && rules.get(d1).get(d2) != null) {
            return rules.get(d1).get(d2);
        }
        return null;
    }

    public static List<Base> getBases() {
        return Bases;
    }

    public static Bilinearform scalarproduct() {
        return bilinearform;
    }

    public static void useCayley() {
        Base[] cayleyBases = { R, I, J, K, L, M, N, O };
        Bases = Arrays.asList(cayleyBases);
        bilinearform = scalarProduct;

        rules = new HashMap<>();
        addRule(R, I, (x, y) -> new Value(I, x * y));
        addRule(R, J, (x, y) -> new Value(J, x * y));
        addRule(R, K, (x, y) -> new Value(K, x * y));
        addRule(R, L, (x, y) -> new Value(L, x * y));
        addRule(R, M, (x, y) -> new Value(M, x * y));
        addRule(R, N, (x, y) -> new Value(N, x * y));
        addRule(R, O, (x, y) -> new Value(O, x * y));

        addRule(I, R, (x, y) -> new Value(I, x * y));
        addRule(I, I, (x, y) -> new Value(R, -x * y));
        addRule(I, J, (x, y) -> new Value(K, x * y));
        addRule(I, K, (x, y) -> new Value(J, -x * y));
        addRule(I, L, (x, y) -> new Value(M, x * y));
        addRule(I, M, (x, y) -> new Value(L, -x * y));
        addRule(I, N, (x, y) -> new Value(O, -x * y));
        addRule(I, O, (x, y) -> new Value(N, x * y));

        addRule(J, R, (x, y) -> new Value(J, x * y));
        addRule(J, I, (x, y) -> new Value(K, -x * y));
        addRule(J, J, (x, y) -> new Value(R, -x * y));
        addRule(J, K, (x, y) -> new Value(I, x * y));
        addRule(J, L, (x, y) -> new Value(N, x * y));
        addRule(J, M, (x, y) -> new Value(O, x * y));
        addRule(J, N, (x, y) -> new Value(L, -x * y));
        addRule(J, O, (x, y) -> new Value(M, -x * y));

        addRule(K, R, (x, y) -> new Value(K, x * y));
        addRule(K, I, (x, y) -> new Value(J, x * y));
        addRule(K, J, (x, y) -> new Value(I, -x * y));
        addRule(K, K, (x, y) -> new Value(R, -x * y));
        addRule(K, L, (x, y) -> new Value(O, x * y));
        addRule(K, M, (x, y) -> new Value(N, -x * y));
        addRule(K, N, (x, y) -> new Value(M, x * y));
        addRule(K, O, (x, y) -> new Value(L, -x * y));

        addRule(L, R, (x, y) -> new Value(L, x * y));
        addRule(L, I, (x, y) -> new Value(M, -x * y));
        addRule(L, J, (x, y) -> new Value(N, -x * y));
        addRule(L, K, (x, y) -> new Value(O, -x * y));
        addRule(L, L, (x, y) -> new Value(R, -x * y));
        addRule(L, M, (x, y) -> new Value(I, x * y));
        addRule(L, N, (x, y) -> new Value(J, x * y));
        addRule(L, O, (x, y) -> new Value(K, x * y));

        addRule(M, R, (x, y) -> new Value(M, x * y));
        addRule(M, I, (x, y) -> new Value(L, x * y));
        addRule(M, J, (x, y) -> new Value(O, -x * y));
        addRule(M, K, (x, y) -> new Value(N, x * y));
        addRule(M, L, (x, y) -> new Value(I, -x * y));
        addRule(M, M, (x, y) -> new Value(R, -x * y));
        addRule(M, N, (x, y) -> new Value(K, -x * y));
        addRule(M, O, (x, y) -> new Value(J, x * y));

        addRule(N, R, (x, y) -> new Value(N, x * y));
        addRule(N, I, (x, y) -> new Value(O, x * y));
        addRule(N, J, (x, y) -> new Value(L, x * y));
        addRule(N, K, (x, y) -> new Value(M, -x * y));
        addRule(N, L, (x, y) -> new Value(J, -x * y));
        addRule(N, M, (x, y) -> new Value(K, x * y));
        addRule(N, N, (x, y) -> new Value(R, -x * y));
        addRule(N, O, (x, y) -> new Value(I, -x * y));

        addRule(O, R, (x, y) -> new Value(O, x * y));
        addRule(O, I, (x, y) -> new Value(N, -x * y));
        addRule(O, J, (x, y) -> new Value(M, x * y));
        addRule(O, K, (x, y) -> new Value(L, x * y));
        addRule(O, L, (x, y) -> new Value(K, -x * y));
        addRule(O, M, (x, y) -> new Value(J, -x * y));
        addRule(O, N, (x, y) -> new Value(I, x * y));
        addRule(O, O, (x, y) -> new Value(R, -x * y));
    }

    public static void useComplex() {
        bilinearform = scalarProduct;
        Base[] complexBases = { R, I };
        Bases = Arrays.asList(complexBases);

        rules = new HashMap<>();
        addRule(R, R, (x, y) -> new Value(R, x * y));
        addRule(R, I, (x, y) -> new Value(I, x * y));
        addRule(I, R, (x, y) -> new Value(I, x * y));
        addRule(I, I, (x, y) -> new Value(R, -x * y));
    }

    public static void useHamilton() {
        bilinearform = scalarProduct;
        Base[] hamiltonBases = { R, I, J, K };
        Bases = Arrays.asList(hamiltonBases);

        rules = new HashMap<>();
        addRule(R, R, (x, y) -> new Value(R, x * y));
        addRule(R, I, (x, y) -> new Value(I, x * y));
        addRule(R, J, (x, y) -> new Value(J, x * y));
        addRule(R, K, (x, y) -> new Value(K, x * y));

        addRule(I, R, (x, y) -> new Value(I, x * y));
        addRule(I, I, (x, y) -> new Value(R, -x * y));
        addRule(I, J, (x, y) -> new Value(K, x * y));
        addRule(I, K, (x, y) -> new Value(J, -x * y));

        addRule(J, R, (x, y) -> new Value(J, x * y));
        addRule(J, I, (x, y) -> new Value(K, -x * y));
        addRule(J, J, (x, y) -> new Value(R, -x * y));
        addRule(J, K, (x, y) -> new Value(I, x * y));

        addRule(K, R, (x, y) -> new Value(K, x * y));
        addRule(K, I, (x, y) -> new Value(J, x * y));
        addRule(K, J, (x, y) -> new Value(I, -x * y));
        addRule(K, K, (x, y) -> new Value(R, -x * y));
    }

    public static void useSplitQuarternion() {
        bilinearform = splitQuaternionScalarProduct;
        Base[] splitQuaternionBases = { R, I, J, K };
        Bases = Arrays.asList(splitQuaternionBases);

        rules = new HashMap<>();
        addRule(R, R, (x, y) -> new Value(R, x * y));
        addRule(R, I, (x, y) -> new Value(I, x * y));
        addRule(R, J, (x, y) -> new Value(J, x * y));
        addRule(R, K, (x, y) -> new Value(K, x * y));

        addRule(I, R, (x, y) -> new Value(I, x * y));
        addRule(I, I, (x, y) -> new Value(R, -x * y));
        addRule(I, J, (x, y) -> new Value(K, x * y));
        addRule(I, K, (x, y) -> new Value(J, -x * y));

        addRule(J, R, (x, y) -> new Value(J, x * y));
        addRule(J, I, (x, y) -> new Value(K, -x * y));
        addRule(J, J, (x, y) -> new Value(R, x * y));
        addRule(J, K, (x, y) -> new Value(I, -x * y));

        addRule(K, R, (x, y) -> new Value(K, x * y));
        addRule(K, I, (x, y) -> new Value(J, x * y));
        addRule(K, J, (x, y) -> new Value(I, x * y));
        addRule(K, K, (x, y) -> new Value(R, x * y));
    }

    public static void useReal() {
        bilinearform = scalarProduct;
        Base[] realBases = { R };
        Bases = Arrays.asList(realBases);
        rules = new HashMap<>();
        addRule(R, R, (x, y) -> new Value(R, x * y));
    }

    /**
     * The scalar product is a symmetrical bilinear form
     */
    private static Bilinearform scalarProduct = (u, v) -> {
        if (u.length != v.length) {
            throw new IllegalArgumentException("Vectors must have the same dimension.");
        }
        Number result = new Number();
        for (int i = 0; i < u.length; i++) {
            result = result.add(u[i].multiply(v[i].conjugate()));
        }

        return result.getValue(R);
    };

    /**
     * This bilinear form does not multiply the scalars, but only their coefficients!
     */
    private static Bilinearform splitQuaternionScalarProduct = (u, v) -> {
        if (u.length != v.length) {
            throw new IllegalArgumentException("Vectors must have the same dimension.");
        }

        double result = 0.0;

        // Iterate over the vector
        for (int i = 0; i < u.length; i++) {
            // Iterate over the i,j,k,....
            for (Base base : getBases()) {
                result += u[i].getValue(base) * v[i].getValue(base);
            }
        }

        return result;
    };

    private static void addRule(Base d1, Base d2, Mult rule) {
        if (rules.get(d1) == null) {
            rules.put(d1, new HashMap<>());
        }
        if (rules.get(d1).get(d2) == null) {
            rules.get(d1).put(d2, rule);
        }
    }

}
