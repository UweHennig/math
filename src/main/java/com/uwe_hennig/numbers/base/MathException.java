/**
 * @(#)MathException.java
 * Copyright (c) 2025 Uwe Hennig
 * All rights reserved.
 */
package com.uwe_hennig.numbers.base;

/**
 * MathException
 * @author Uwe Hennig
 */
@SuppressWarnings("serial")
public class MathException extends RuntimeException {
    public MathException(String msg) {
        super(msg);
    }
}
