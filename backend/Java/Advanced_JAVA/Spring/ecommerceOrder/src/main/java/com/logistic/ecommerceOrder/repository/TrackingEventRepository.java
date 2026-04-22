package com.logistic.ecommerceOrder.repository;

import com.logistic.ecommerceOrder.entity.TrackingEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingEventRepository extends JpaRepository<TrackingEvent, Long> {
}