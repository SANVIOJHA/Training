package com.sprintsmartcourier.delivery.exception;


public class DeliveryNotFoundException extends RuntimeException {

    public DeliveryNotFoundException() {
        super("Address not found");
    }

    public DeliveryNotFoundException(String message) {
        super(message);
    }
}
