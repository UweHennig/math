/**
 * @(#)AlgebraicNumberStringParser.java
 * Copyright (c) 2025 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import com.uwe_hennig.numbers.base.AlgebraicContext;
import com.uwe_hennig.numbers.base.MathException;
import com.uwe_hennig.numbers.base.ScalarBase;
import com.uwe_hennig.numbers.base.ScalarValue;

/**
 * AlgebraicNumberStringParser
 * @author Uwe Hennig
 */
public class AlgebraicNumberStringParser {
    private final AlgebraicContext context;
    private final ScalarValue[] scalarValues = new ScalarValue[8];

    public AlgebraicNumberStringParser(AlgebraicContext context) {
        this.context = context;
    }

    public AlgebraicNumber parse(String input) {
        try {
            IntStream.range(0, 8).forEach(i -> scalarValues[i] = new ScalarValue(0.0, ScalarBase.of(i)));

            Pattern termPattern = Pattern.compile("([+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)?(?:[eE][+-]?\\d+)?)([a-zA-Z]*)");
            Matcher matcher = termPattern.matcher(input.replaceAll("\\s+", ""));

            while (matcher.find()) {
                String numberPart = matcher.group(1);
                String suffix = matcher.group(2);

                if ((numberPart == null || numberPart.isEmpty()) && (suffix == null || suffix.isEmpty())) {
                    continue;
                }

                double value = numberPart.isEmpty() || numberPart.equals("+") ? 1.0 :
                               numberPart.equals("-") ? -1.0 :
                               Double.parseDouble(numberPart);

                ScalarBase base = suffix.isEmpty() ? ScalarBase.R : ScalarBase.valueOf(suffix.toUpperCase());

                if (!context.isValidBase(base)) {
                    throw new MathException("Base " + base + " not valid in context " + context.getClass().getSimpleName());
                }

                this.scalarValues[base.id()] = new ScalarValue(value, base);
            }

            return new AlgebraicNumber(context, scalarValues);
        } catch (NumberFormatException e) {
            throw new MathException("NumberFormatException thrown!", e);
        }
    }
}
