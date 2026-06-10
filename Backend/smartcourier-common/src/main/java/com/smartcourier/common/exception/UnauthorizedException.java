package com.smartcourier.common.exception;

/**
 * Thrown when authentication or authorization fails.
 */
public class UnauthorizedException extends SmartCourierException {

    public UnauthorizedException(String message) {
        super(message);
    }
}
