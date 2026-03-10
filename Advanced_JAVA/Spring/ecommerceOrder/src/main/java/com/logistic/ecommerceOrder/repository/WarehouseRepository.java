package com.logistic.ecommerceOrder.repository;

import com.logistic.ecommerceOrder.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
