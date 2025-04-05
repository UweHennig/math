/**
 * @(#)TestNumbers.java
 * Copyright (c) 2023 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers;


import static com.uwe_hennig.numbers.base.Base.I;
import static com.uwe_hennig.numbers.base.Base.J;
import static com.uwe_hennig.numbers.base.Base.K;
import static com.uwe_hennig.numbers.base.Base.L;
import static com.uwe_hennig.numbers.base.Base.M;
import static com.uwe_hennig.numbers.base.Base.N;
import static com.uwe_hennig.numbers.base.Base.O;
import static com.uwe_hennig.numbers.base.Base.R;
import static com.uwe_hennig.numbers.base.Number.ONE;
import static com.uwe_hennig.numbers.base.Number.ZERO;
import static com.uwe_hennig.numbers.base.Value.iValue;
import static com.uwe_hennig.numbers.base.Value.jValue;
import static com.uwe_hennig.numbers.base.Value.kValue;
import static com.uwe_hennig.numbers.base.Value.lValue;
import static com.uwe_hennig.numbers.base.Value.mValue;
import static com.uwe_hennig.numbers.base.Value.nValue;
import static com.uwe_hennig.numbers.base.Value.oValue;
import static com.uwe_hennig.numbers.base.Value.rValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.uwe_hennig.numbers.base.Number;
import com.uwe_hennig.numbers.base.Rules;
import com.uwe_hennig.numbers.base.Value;

/**
 * TestNumbers
 * @author Uwe Hennig
 */
public class TestNumbers {
    @Test
    public void testDivisionAlgebraBase() {
        printHeadline("testDivisionAlgebraBase");
        Rules.useSplitQuarternion();
        Value result;

        // r * r = 1
        Value r = rValue(1.0);
        result = r.mult(r);
        assertEquals("Error: r * r = 1", 1.0, result.value(), 0.00001);
        assertEquals("Invalid scalar size!", R, result.base());

        // i * i = -1
        Value i = iValue(1.0);
        result = i.mult(i);
        assertEquals("Error: i * i = -1", -1.0, result.value(), 0.00001);
        assertEquals("Invalid scalar size!", R, result.base());

        // j * j = 1
        Value j = jValue(1.0);
        result = j.mult(j);
        assertEquals("Error: j * j = 1", 1.0, result.value(), 0.00001);
        assertEquals("Invalid scalar size!", R, result.base());

        // k * k = 1
        Value k = kValue(1.0);
        result = k.mult(k);
        assertEquals("Error: k * k = 1", 1.0, result.value(), 0.00001);
        assertEquals("Size Error: k * k = 1", R, result.base());

        // i * j = k
        i = iValue(1.0);
        j = jValue(1.0);
        result = i.mult(j);
        assertEquals("Error: i * j = k", 1.0, result.value(), 0.00001);
        assertEquals("Scalar size Error: i * j = k", K, result.base());

        // j * i = -k
        j = jValue(1.0);
        i = iValue(1.0);
        result = j.mult(i);
        assertEquals("Error: j * i = -k", -1.0, result.value(), 0.00001);
        assertEquals("Scalar size Error: j * i = -k", K, result.base());

        // j * k = -i
        j = jValue(1.0);
        k = kValue(1.0);
        result = j.mult(k);
        assertEquals("Error: j * k = -i", -1.0, result.value(), 0.00001);
        assertEquals("Scalar size Error: j * k = -i", I, result.base());

        // k * j = i
        k = kValue(1.0);
        j = jValue(1.0);
        result = k.mult(j);
        assertEquals("Error: k * j = i", 1.0, result.value(), 0.00001);
        assertEquals("Scalar size Error: k * j = i", I, result.base());

        // k * i = j
        k = kValue(1.0);
        i = iValue(1.0);
        result = k.mult(i);
        assertEquals("Error: k * i = j", 1.0, result.value(), 0.00001);
        assertEquals("Scalar size Error: k * i = j", J, result.base());

        // i * k = -j
        i = iValue(1.0);
        k = kValue(1.0);
        result = i.mult(k);
        assertEquals("Error: i * k = -j", -1.0, result.value(), 0.00001);
        assertEquals("Scalar size Error: i * k = -j", J, result.base());
    }

    @Test
    public void testAdd() {
        printHeadline("testAdd");
        Rules.useComplex();
        Number a = new Number(0.0D, 1.0d);
        Number b = new Number(1.0D, 0.0d);
        Number expected = new Number(1.0D, 1.0D);
        assertEquals("Generic add fails!", expected, a.add(b));
    }

    @Test
    public void testNorm() {
        printHeadline("testNorm");
        Rules.useComplex();
        Number i = new Number(0.0D, 1.0D);
        Double result = i.norm();
        assertNotNull("Error, norm is null!", result);
        assertTrue("Error, norm value < 0.0", result > 0.0D);
    }

    @Test
    public void testInvers() {
        printHeadline("testInvers");
        Rules.useComplex();
        Number r = new Number(5.0);
        Number result = r.invers();
        Number expected = new Number(0.2);
        assertEquals("Generic invers fails!", expected, result);

        Number i = new Number(0.0D, 1.0D);
        result = i.invers();
        expected = new Number(0.0d, -1.0D);
        assertEquals("Generic invers fails!", expected, result);
    }

    @Test
    public void testNormalize() {
        printHeadline("testNormalize");
        Rules.useComplex();
        Number n = new Number(1.0d, 1.0D);
        Number result = n.normalize();
        Number expected = new Number(Math.cos(Math.PI / 4), Math.cos(Math.PI / 4));
        assertEquals("Error, invalid normalized Generic!", expected, result);
    }

    @Test
    public void testConst() {
        printHeadline("testConst");
        Rules.useComplex();
        assertNotNull(ONE());
        assertNotNull(ZERO());

        assertEquals("ONE not valid!", new Number(1.0D), ONE());
        assertEquals("ZERO not valid!", new Number(0.0D), ZERO());

        Rules.useComplex();
        Number z = ONE().add(new Number(0.0D, 1.0D));
        assertNotNull("Value is null!", z);
        assertEquals("Values ar not equals", new Number(1, 1), z);
    }

    @Test
    public void testEquals() {
        printHeadline("testEquals");
        Rules.useComplex();

        Rules.useSplitQuarternion();
        Number a = new Number(1.0D, 1.0D);
        Number b = new Number(1.0D, 1.0D, 0.0D);
        assertTrue("Values are not equal!", a.equals(b));

        a = new Number(1.0D, 1.0D, 0.0D);
        b = new Number(1.0D, 1.0D);
        assertTrue("Values are not equal!", a.equals(b));
    }

    @Test
    public void testPrint() {
        printHeadline("testPrint");
        Rules.useReal();
        System.out.println("Real: \t\t" + new Number(Math.PI));
        System.out.println("Real: \t\t" + new Number(-1.3D));

        Rules.useComplex();
        System.out.println("Complex: \t" + new Number(0, 2));
        System.out.println("Complex: \t" + new Number(1, -2));
        System.out.println("Complex: \t" + new Number(-1, 2));

        Rules.useHamilton();
        System.out.println("Hamilton: \t" + ZERO().with(R, 2.3).with(K, 12.3));
        System.out.println("Hamilton: \t" + ZERO().with(R, -2.23).with(K, 12.3));
        System.out.println("Hamilton: \t" + ZERO().with(R, 2.23).with(K, -12.3));

        Rules.useSplitQuarternion();
        System.out.println("Split: \t\t" + ZERO().with(R, 2.3).with(K, 12.3));
        System.out.println("Split: \t\t" + ZERO().with(R, -2.23).with(K, 12.3));
        System.out.println("Split: \t\t" + ZERO().with(R, 2.23).with(K, -12.3));

        Rules.useCayley();
        System.out.println("Cayley: \t" + ZERO().with(R, 1.0D));
        System.out.println("Cayley: \t" + new Number(1, -2, 3, -4, 5, -6, 7, -8));
        System.out.println("Cayley: \t" + new Number(-11, -2, 3, -4, 5, -6, 7, 8));
        System.out.println("Cayley: \t" + new Number());
        System.out.println("Cayley: \t" + new Number(1D));
    }

    @Test
    public void testComplex() {
        printHeadline("testComplex");
        Complex complex = new Complex(1.0, 2.0);
        System.out.println(complex);

        Complex factor = new Complex(0.0, 1.0);
        Complex result = (Complex) complex.multiply(factor);
        assertEquals(new Complex(-2.0, 1.0), result);
    }

    @Test
    public void testHamilton() {
        printHeadline("testHamilton");
        Hamilton hamilton = new Hamilton(1.0, 2.0, 3.0, 4.0);
        System.out.println(hamilton);

        Hamilton factor = new Hamilton(2.0);
        Hamilton result = (Hamilton) hamilton.multiply(factor);
        assertEquals(new Hamilton(2.0, 4.0, 6.0, 8.0), result);
    }

    @Test
    public void testCayley() {
        printHeadline("testCayley");
        Cayley cayley = new Cayley(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0);
        System.out.println(cayley);
    }

    @Test
    public void testReal() {
        printHeadline("testReal");
        Real real= new Real(1.0);
        System.out.println(real);
    }

    private void printHeadline(String msg) {
        System.out.println();
        System.out.println(msg);
        System.out.println("------------------------------");
    }

}
