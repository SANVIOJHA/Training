package com.smartcourier.tracking.repository;

import com.smartcourier.tracking.entity.TrackingEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackingRepository extends JpaRepository<TrackingEvent, Long> {

    List<TrackingEvent> findByDeliveryId(Long deliveryId);

    List<TrackingEvent> findByStatus(String status);
}