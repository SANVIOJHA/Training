package com.cap;

import org.springframework.stereotype.Component;

/*
 * Another implementation of PaymentService.
 * Spring will create this bean also.
 */
@Component("upiPayment")
public class UpiPayment implements PaymentService {

    @Override
    public void pay(double amount) {

        System.out.println("The amount for UPI is " + amount);
    }
}