package com.smartcourier.common.exception;

/**
 * Thrown when a requested resource is not found.
 */
public class ResourceNotFoundException extends SmartCourierException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
