package com.smartcourier.delivery.strategy;

/**
 * Strategy interface for calculating delivery pricing.
 */
public interface PricingStrategy {

    double calculatePrice(double weight);

    String getStrategyName();
}
