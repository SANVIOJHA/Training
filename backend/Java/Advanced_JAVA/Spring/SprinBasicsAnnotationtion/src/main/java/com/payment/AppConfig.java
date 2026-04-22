package com.payment;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public PaymentService creditCardPayment() {
        return new CreditCard();
    }

    @Bean
    public PaymentService upiPayment() {
        return new UpiPayment();
    }

    @Bean
    public CheckoutService checkoutService(
            @Qualifier("upiPayment") PaymentService paymentService) {
        return new CheckoutService(paymentService);
    }
}