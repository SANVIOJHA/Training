package com.smartcourier.delivery.strategy;

import org.springframework.stereotype.Component;

/**
 * Standard pricing strategy for same-city deliveries.
 * Base rate + weight-based charge.
 */
@Component
public class StandardPricingStrategy implements PricingStrategy {

    private static final double BASE_RATE = 50.0;
    private static final double PER_KG_RATE = 10.0;

    @Override
    public double calculatePrice(double weight) {
        return BASE_RATE + (weight * PER_KG_RATE);
    }

    @Override
    public String getStrategyName() {
        return "STANDARD";
    }
}
