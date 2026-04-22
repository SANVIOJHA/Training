package com.payment;

//import org.springframework.stereotype.Component;


public class CreditCard implements PaymentService{


    @Override
    public void pay(double amount) {
        System.out.println(" the amount for credit card is "+amount);
    }
}
