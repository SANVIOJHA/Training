package com.smartcourier.delivery.repository;

import com.smartcourier.delivery.entity.Delivery;
import com.smartcourier.delivery.enums.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    List<Delivery> findByStatus(DeliveryStatus status);

    List<Delivery> findByAssignedAgent(String agent);

    List<Delivery> findByCustomerUsername(String username);
}