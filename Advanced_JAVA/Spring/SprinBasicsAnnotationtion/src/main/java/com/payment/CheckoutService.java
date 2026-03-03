package com.payment;

import com.payment.PaymentService;
import org.springframework.beans.factory.annotation.Qualifier;

public class CheckoutService {

    private PaymentService paymentService;

    // Using Qualifier to select CreditCardPayment
    public CheckoutService(@Qualifier("upiPayment") PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void checkout(double amount) {
        paymentService.pay(amount);
    }
}