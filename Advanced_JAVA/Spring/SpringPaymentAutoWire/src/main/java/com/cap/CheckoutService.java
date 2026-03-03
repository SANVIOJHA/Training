package com.cap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/*
 * CheckoutService depends on PaymentService.
 * Instead of creating object manually,
 * we let Spring inject it.
 */
@Component
public class CheckoutService {

    private PaymentService paymentService;

    /*
     * @Autowired tells Spring:
     * "Inject a bean here automatically"
     *
     * Since two implementations exist,
     * we use @Qualifier to specify which one.
     */
    @Autowired
    public CheckoutService(
            @Qualifier("creditCardPayment") PaymentService paymentService) {

        this.paymentService = paymentService;
    }

    /*
     * This method calls the injected payment method.
     */
    public void checkout(double amount) {

        paymentService.pay(amount);
    }
}