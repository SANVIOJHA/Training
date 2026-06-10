-- V1: Create deliveries table for delivery-service
CREATE TABLE IF NOT EXISTS delivery (
    id BIGINT NOT NULL AUTO_INCREMENT,
    tracking_number VARCHAR(100) NOT NULL,
    customer_username VARCHAR(100) NOT NULL,
    sender_name VARCHAR(255) DEFAULT NULL,
    customer_mobile_number VARCHAR(20) DEFAULT NULL,
    receiver_name VARCHAR(255) DEFAULT NULL,
    receiver_phone VARCHAR(20) DEFAULT NULL,
    source VARCHAR(500) DEFAULT NULL,
    destination VARCHAR(500) DEFAULT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'CREATED',
    assigned_agent VARCHAR(100) DEFAULT NULL,
    price DOUBLE DEFAULT NULL,
    weight DOUBLE DEFAULT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uk_delivery_tracking_number (tracking_number),
    INDEX idx_delivery_customer (customer_username),
    INDEX idx_delivery_status (status),
    INDEX idx_delivery_agent (assigned_agent),
    INDEX idx_delivery_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
