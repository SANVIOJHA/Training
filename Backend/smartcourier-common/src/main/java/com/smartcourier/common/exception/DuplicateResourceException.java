package com.smartcourier.common.exception;

/**
 * Thrown when a duplicate resource is detected (e.g., duplicate email, duplicate booking).
 */
public class DuplicateResourceException extends SmartCourierException {

    public DuplicateResourceException(String message) {
        super(message);
    }
}
