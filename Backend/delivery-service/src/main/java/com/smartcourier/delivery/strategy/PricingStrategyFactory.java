package com.smartcourier.delivery.strategy;

import org.springframework.stereotype.Component;

/**
 * Factory for selecting the appropriate pricing strategy.
 * Uses source/destination comparison to determine pricing tier.
 */
@Component
public class PricingStrategyFactory {

    private final StandardPricingStrategy standardPricing;
    private final ExpressPricingStrategy expressPricing;

    public PricingStrategyFactory(StandardPricingStrategy standardPricing,
                                  ExpressPricingStrategy expressPricing) {
        this.standardPricing = standardPricing;
        this.expressPricing = expressPricing;
    }

    /**
     * Determine pricing strategy based on source and destination.
     * Simple heuristic: if source and destination share the same city prefix, use standard pricing.
     * Otherwise, use express (inter-city) pricing.
     */
    public PricingStrategy getStrategy(String source, String destination) {
        if (source == null || destination == null) {
            return standardPricing;
        }

        // Simple heuristic: same first word = same city
        String sourceCity = source.trim().split("[,\\s]+")[0].toLowerCase();
        String destCity = destination.trim().split("[,\\s]+")[0].toLowerCase();

        if (sourceCity.equals(destCity)) {
            return standardPricing;
        }
        return expressPricing;
    }
}
