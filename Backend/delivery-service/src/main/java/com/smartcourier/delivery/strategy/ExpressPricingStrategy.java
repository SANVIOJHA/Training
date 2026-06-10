package com.smartcourier.delivery.strategy;

import org.springframework.stereotype.Component;

/**
 * Express pricing strategy for inter-city deliveries.
 * Higher base rate with premium weight charge.
 */
@Component
public class ExpressPricingStrategy implements PricingStrategy {

    private static final double BASE_RATE = 150.0;
    private static final double PER_KG_RATE = 25.0;

    @Override
    public double calculatePrice(double weight) {
        return BASE_RATE + (weight * PER_KG_RATE);
    }

    @Override
    public String getStrategyName() {
        return "EXPRESS";
    }
}
