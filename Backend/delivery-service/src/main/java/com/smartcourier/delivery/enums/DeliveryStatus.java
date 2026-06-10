package com.smartcourier.delivery.enums;

/**
 * DeliveryStatus defines allowed lifecycle states
 * Prevents invalid string values in DB
 */
public enum DeliveryStatus {

    // --- New Logistics Workflow Statuses ---
    PENDING_DISPATCH,
    PICKED_UP,
    ARRIVED_AT_HUB,
    DISPATCHED_FROM_HUB,
    IN_TRANSIT,
    REACHED_DESTINATION_HUB,
    OUT_FOR_DELIVERY,
    DELIVERED,
    DELIVERY_FAILED,

    // --- Legacy / Backward Compatibility Statuses ---
    @Deprecated CREATED,
    @Deprecated BOOKED,
    @Deprecated SHIPPED,
    @Deprecated DELAYED,
    @Deprecated FAILED,
    @Deprecated RETURNED,
    @Deprecated CANCELLED
}