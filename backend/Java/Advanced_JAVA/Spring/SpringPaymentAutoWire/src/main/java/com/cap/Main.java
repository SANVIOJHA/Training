package com.cap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 * Main class - starting point of application
 */
public class Main {

    public static void main(String[] args) {

        /*
         * Create Spring Container
         * It reads AppConfig class
         */
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        /*
         * Get CheckoutService bean from Spring container
         * We are NOT creating object using new keyword
         */
        CheckoutService checkoutService =
                context.getBean(CheckoutService.class);

        /*
         * Call checkout method
         */
        checkoutService.checkout(5000);
    }
}