package com.smartcourier.common.constant;

/**
 * Centralized constants for SmartCourier microservices.
 * Prevents magic strings scattered across services.
 */
public final class SmartCourierConstants {

    private SmartCourierConstants() {
        // Utility class — no instantiation
    }

    // ==================== ROLES ====================
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_CUSTOMER = "CUSTOMER";

    // ==================== RABBITMQ ====================
    public static final String AUTH_EXCHANGE = "auth_exchange";
    public static final String USER_REGISTERED_QUEUE = "user_registered_queue";
    public static final String USER_REGISTERED_ROUTING_KEY = "user.registered";

    public static final String DELIVERY_EVENTS_EXCHANGE = "smartcourier.events.exchange";
    public static final String DELIVERY_STATUS_QUEUE = "tracking.delivery.status.queue";
    public static final String DELIVERY_STATUS_ROUTING_KEY = "delivery.status.changed";

    // ==================== HEADERS ====================
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_X_USER = "X-User";
    public static final String HEADER_X_ROLE = "X-Role";
    public static final String BEARER_PREFIX = "Bearer ";

    // ==================== TRACKING ====================
    public static final String TRACKING_PREFIX = "SC-";

    // ==================== DEFAULTS ====================
    public static final String DEFAULT_LOCATION = "Transit hub";
}
