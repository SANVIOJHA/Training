package com.smartcourier.delivery.enums;

/**
 * DeliveryStatus defines allowed lifecycle states
 * Prevents invalid string values in DB
 */
public enum DeliveryStatus {

    DRAFT,
    BOOKED,
    PICKED_UP,
    IN_TRANSIT,
    OUT_FOR_DELIVERY,
    SHIPPED,
    DELIVERED,
    DELAYED,
    FAILED,
    RETURNED,
    CANCELLED
}
