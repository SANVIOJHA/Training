package com.smartcourier.common.exception;

/**
 * Base exception for all SmartCourier business exceptions.
 * Subclass this for domain-specific exceptions.
 */
public abstract class SmartCourierException extends RuntimeException {

    protected SmartCourierException(String message) {
        super(message);
    }

    protected SmartCourierException(String message, Throwable cause) {
        super(message, cause);
    }
}
