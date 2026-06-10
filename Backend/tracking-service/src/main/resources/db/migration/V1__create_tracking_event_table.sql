-- V1: Create tracking_event table for tracking-service
CREATE TABLE IF NOT EXISTS tracking_event (
    id BIGINT NOT NULL AUTO_INCREMENT,
    delivery_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    location VARCHAR(500) DEFAULT NULL,
    description VARCHAR(1000) DEFAULT NULL,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    INDEX idx_tracking_delivery_id (delivery_id),
    INDEX idx_tracking_status (status),
    INDEX idx_tracking_timestamp (timestamp)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
