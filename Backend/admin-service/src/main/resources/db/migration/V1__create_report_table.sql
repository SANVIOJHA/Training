-- V1: Create report table for admin-service
CREATE TABLE IF NOT EXISTS report (
    id BIGINT NOT NULL AUTO_INCREMENT,
    report_type VARCHAR(100) DEFAULT NULL,
    report_date DATE DEFAULT NULL,
    from_date DATE DEFAULT NULL,
    to_date DATE DEFAULT NULL,
    report_data VARCHAR(2000) DEFAULT NULL,
    generated_by VARCHAR(100) DEFAULT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    INDEX idx_report_type (report_type),
    INDEX idx_report_date (report_date),
    INDEX idx_report_generated_by (generated_by)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
