package com.prac.eccom;

import java.util.List;

public class PricingService {

    public double calculateTotal(List<OrderItem> items,
                                 boolean isPremium,
                                 boolean isFirstTimeUser,
                                 String coupon) {

        if (items == null || items.isEmpty()) {
            throw new EmptyOrderException("Order cannot be empty");
        }

        double total = 0;

        for (OrderItem item : items) {

            double itemTotal = item.getPrice() * item.getQuantity();

            // Tax rules
            if ("ELECTRONICS".equalsIgnoreCase(item.getCategory())) {
                itemTotal += itemTotal * 0.18;   // 18% tax
            }

            total += itemTotal;
        }

        // Premium discount
        if (isPremium) {
            total -= total * 0.10;
        }

        // Coupon logic
        if ("FLAT200".equalsIgnoreCase(coupon) && total > 1000) {
            total -= 200;
        }

        // Final amount cannot be negative
        return Math.max(total, 0);
    }
}

