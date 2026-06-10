package com.smartcourier.common.exception;

/**
 * Thrown when a business rule or validation rule is violated.
 */
public class BusinessValidationException extends SmartCourierException {

    public BusinessValidationException(String message) {
        super(message);
    }
}
