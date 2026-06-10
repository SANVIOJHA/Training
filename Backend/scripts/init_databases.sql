-- SmartCourier Database Initialization Script
-- Run this once to create the 4 microservice databases.
-- Each service uses Flyway to manage its own schema.

CREATE DATABASE IF NOT EXISTS smartcourier_auth_db;
CREATE DATABASE IF NOT EXISTS smartcourier_delivery_db;
CREATE DATABASE IF NOT EXISTS smartcourier_tracking_db;
CREATE DATABASE IF NOT EXISTS smartcourier_admin_db;

-- Verify databases created
SHOW DATABASES LIKE 'smartcourier_%';
