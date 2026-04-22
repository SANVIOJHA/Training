package com.sprintsmartcourier.delivery.exception;

public class PackageNotFoundException extends RuntimeException {

    public PackageNotFoundException() {
        super("Package not found");
    }

    public PackageNotFoundException(String message) {
        super(message);
    }
}