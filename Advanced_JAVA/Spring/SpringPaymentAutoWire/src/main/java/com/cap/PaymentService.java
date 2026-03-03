package com.cap;

/*
 * This is an interface.
 * It defines the contract for all payment types.
 * Any payment method must implement pay().
 */
public interface PaymentService {

    // Method to process payment
    void pay(double amount);
}