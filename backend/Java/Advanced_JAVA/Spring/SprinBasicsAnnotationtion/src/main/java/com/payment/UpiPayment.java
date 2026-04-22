package com.payment;

public class UpiPayment implements PaymentService {
    //for payment --- 2 implementation class ----use all the annotations
@Override
    public void pay(double amount){
    System.out.println("the amount for upi is "+amount);
}
}
