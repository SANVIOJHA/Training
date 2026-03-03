package com.payment;

import com.payment.CheckoutService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        CheckoutService checkoutService =
                context.getBean(CheckoutService.class);

        checkoutService.checkout(5000);
    }
}