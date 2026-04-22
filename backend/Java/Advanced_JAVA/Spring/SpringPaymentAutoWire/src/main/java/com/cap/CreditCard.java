package com.cap;

import org.springframework.stereotype.Component;

/*
 * @Component tells Spring:
 * "Create an object (bean) of this class automatically"
 *
 * The name "creditCardPayment" becomes the bean name.
 */
@Component("creditCardPayment")
public class CreditCard implements PaymentService {

    // Implementation of pay() method
    @Override
    public void pay(double amount) {

        // Prints payment message
        System.out.println("The amount for credit card is " + amount);
    }
}