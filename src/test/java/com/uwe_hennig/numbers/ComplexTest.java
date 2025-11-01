/**
 * @(#)ComplexTest.java
 * Copyright (c) 2025 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers;

import static com.uwe_hennig.numbers.AlgebraicNumber.cayley;
import static com.uwe_hennig.numbers.AlgebraicNumber.complex;
import static com.uwe_hennig.numbers.AlgebraicNumber.hamilton;
import static com.uwe_hennig.numbers.AlgebraicNumber.real;
import static com.uwe_hennig.numbers.AlgebraicNumber.splitQuaternion;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.uwe_hennig.numbers.base.CayleyContext;
import com.uwe_hennig.numbers.base.ComplexContext;

/**
 * ComplexTest
 *
 * @author Uwe Hennig
 */
public class ComplexTest {

    @Test
    public void testParser() {
        try {
            printHeadline("testParser");
            final String[] testStrings = {
                "i+k+o",
                "-1+3i",
                "1.234e-5+5.679e-8i",
                "1.1e-1+2e-1i-3e+1k",
                "1.234e-5",
                "3.0 + 4.1i",
                "1 -2i+j",
                "-3.0-4i",
                "4i",
                "3.0",
                " 3  + 4.0 i " };

            for (int i = 0; i < testStrings.length; i++) {
                AlgebraicNumberStringParser builder = new AlgebraicNumberStringParser(CayleyContext.getInstance());
                System.out.println("Testing: '" + testStrings[i] + "'");
                AlgebraicNumber n = builder.parse(testStrings[i]);
                assertNotNull(n);
                System.out.println("Result:  '" + n + "'");
                System.out.println();
            }
            AlgebraicNumberStringParser p = new AlgebraicNumberStringParser(ComplexContext.getInstance());
            AlgebraicNumber number = p.parse("3+1i").add(p.parse("2-3i"));
            System.out.println("complex test: (3 + i) + (2 - 3i) = (5 - 2i =)" + number);
            AlgebraicNumber expected = complex().r(5).i(-2).create();
            assertEquals(expected, number);

        } catch (Exception e) {
            fail("Parsing failed: " + e.getLocalizedMessage());
        }
    }

    @Test
    public void testSplitQuaternion() {
        printHeadline("testSplitQuaternion");
        AlgebraicNumber exprected;
        AlgebraicNumber result;
        AlgebraicNumber a;
        AlgebraicNumber b;

        // r * r = 1
        a = splitQuaternion().r(1).create();
        b = splitQuaternion().r(1).create();
        exprected = splitQuaternion().r(1).create();
        result = a.multiply(b);
        assertEquals("Error: r * r = 1", exprected, result);

        // i * i = -1
        a = splitQuaternion().i(1).create();
        b = splitQuaternion().i(1).create();
        exprected = splitQuaternion().r(-1).create();
        result = a.multiply(b);
        assertEquals("Error: i * i = -1", exprected, result);

        // j * j = 1
        a = splitQuaternion().j(1).create();
        b = splitQuaternion().j(1).create();
        exprected = splitQuaternion().r(1).create();
        result = a.multiply(b);
        assertEquals("Error: j * j = 1", exprected, result);

        // k * k = 1
        a = splitQuaternion().k(1).create();
        b = splitQuaternion().k(1).create();
        exprected = splitQuaternion().r(1).create();
        result = a.multiply(b);
        assertEquals("Error: k * k = 1", exprected, result);

        // i * j = k
        a = splitQuaternion().i(1).create();
        b = splitQuaternion().j(1).create();
        exprected = splitQuaternion().k(1).create();
        result = a.multiply(b);
        assertEquals("Error: i * j = k", exprected, result);

        // j * i = -k
        a = splitQuaternion().j(1).create();
        b = splitQuaternion().i(1).create();
        exprected = splitQuaternion().k(-1).create();
        result = a.multiply(b);
        assertEquals("Error: j * i = -k", exprected, result);

        // j * k = -i
        a = splitQuaternion().j(1).create();
        b = splitQuaternion().k(1).create();
        exprected = splitQuaternion().i(-1).create();
        result = a.multiply(b);
        assertEquals("Error: j * k = -i", exprected, result);

        // k * j = i
        a = splitQuaternion().k(1).create();
        b = splitQuaternion().j(1).create();
        exprected = splitQuaternion().i(1).create();
        result = a.multiply(b);
        assertEquals("Error: k * j = i", exprected, result);

        // k * i = j
        a = splitQuaternion().k(1).create();
        b = splitQuaternion().i(1).create();
        exprected = splitQuaternion().j(1).create();
        result = a.multiply(b);
        assertEquals("Error: k * i = j", exprected, result);

        // i * k = -j
        a = splitQuaternion().i(1).create();
        b = splitQuaternion().k(1).create();
        exprected = splitQuaternion().j(-1).create();
        result = a.multiply(b);
        assertEquals("Error: i * k = -j", exprected, result);
    }

    @Test
    public void testAdd() {
        printHeadline("testAdd");
        AlgebraicNumber a = complex().r(0).i(1).create();
        AlgebraicNumber b = complex().r(1).i(0).create();
        AlgebraicNumber expected = complex().r(1).i(1).create();
        assertEquals("Generic add fails!", expected, a.add(b));
    }

    @Test
    public void testNorm() {
        printHeadline("testNorm");
        AlgebraicNumber i = complex().r(0).i(1).create();
        Double result = i.norm();
        assertNotNull("Error, norm is null!", result);
        assertTrue("Error, norm value < 0.0", result > 0.0D);
    }

    @Test
    public void testInvers() {
        printHeadline("testInvers");
        AlgebraicNumber r = complex().r(5.0).create();
        AlgebraicNumber result = r.invers();
        AlgebraicNumber expected = complex().r(0.2).create();
        assertEquals("Generic invers fails!", expected, result);

        AlgebraicNumber i = complex().r(0).i(1).create();
        result = i.invers();
        expected = complex().r(0).i(-1).create();
        assertEquals("Generic invers fails!", expected, result);
    }

    @Test
    public void testNormalize() {
        printHeadline("testNormalize");
        AlgebraicNumber n = complex().r(1).i(1).create();
        AlgebraicNumber result = n.normalize();
        AlgebraicNumber expected = complex().r(Math.cos(Math.PI / 4)).i(Math.cos(Math.PI / 4)).create();
        assertEquals("Error, invalid normalized Generic!", expected, result);
    }

    @Test
    public void testEquals() {
        printHeadline("testEquals");
        AlgebraicNumber a = complex().r(1).i(1).create();
        AlgebraicNumber b = complex().r(1).i(1).j(0).create();
        assertEquals("Values are not equal!", a, b);

        a = complex().r(1).i(1).j(0).create();
        b = complex().r(1).i(1).create();
        assertEquals("Values are not equal!", a, b);
    }

    @Test
    public void testPrint() {
        try {
            printHeadline("testPrint");
            System.out.println("Real: \t\t" + real().r(Math.PI).create());
            System.out.println("Real: \t\t" + real().r(-1.3D).create());

            System.out.println("Complex: \t" + complex().r(0).i(2).create());
            System.out.println("Complex: \t" + complex().r(1).i(-2).create());
            System.out.println("Complex: \t" + complex().r(-1).i(2).create());

            System.out.println("Hamilton: \t" + hamilton().r(2.3).k(12.3).create());
            System.out.println("Hamilton: \t" + hamilton().r(-2.23).k(12.3).create());
            System.out.println("Hamilton: \t" + hamilton().r(2.23).k(-12.3).create());

            System.out.println("Split: \t\t" + splitQuaternion().r(2.3).k(12.3).create());
            System.out.println("Split: \t\t" + splitQuaternion().r(-2.23).k(12.3).create());
            System.out.println("Split: \t\t" + splitQuaternion().r(2.23).k(-12.3).create());

            System.out.println("Cayley: \t" + cayley().r(-11).i(-2).j(3).k(-4).l(5).m(-6).o(8).create());
            System.out.println("Cayley: \t" + cayley().r(-11).i(-2).j(3).k(-4).l(5).m(-6).n(7).o(8).create());
            System.out.println("Cayley: \t" + cayley().r(0).create());
            System.out.println("Cayley: \t" + cayley().r(1).create());
        } catch (Exception e) {
            fail("Exception: " + e.getLocalizedMessage());
        }
    }

    private void printHeadline(String msg) {
        System.out.println(msg);
        System.out.println("------------------------------");
    }

}
