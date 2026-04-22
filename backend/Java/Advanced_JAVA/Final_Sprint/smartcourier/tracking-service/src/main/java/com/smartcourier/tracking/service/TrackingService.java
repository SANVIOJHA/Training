package com.smartcourier.tracking.service;

import com.smartcourier.tracking.entity.TrackingEvent;

import java.util.List;

public interface TrackingService {

    TrackingEvent addEvent(TrackingEvent event);

    List<TrackingEvent> getByDeliveryId(Long deliveryId);

    TrackingEvent getLatest(Long deliveryId);

    List<TrackingEvent> getByStatus(String status);

    void delete(Long id);
}