package com.smartcourier.tracking.service;

import com.smartcourier.tracking.entity.DeliveryProofRecord;
import com.smartcourier.tracking.entity.DocumentRecord;
import com.smartcourier.tracking.entity.TrackingEvent;

import java.time.LocalDateTime;
import java.util.List;

public interface TrackingService {

    TrackingEvent addEvent(TrackingEvent event);

    List<TrackingEvent> getByDeliveryId(Long deliveryId);

    List<TrackingEvent> getByTrackingNumber(String trackingNumber);

    TrackingEvent getLatest(Long deliveryId);

    TrackingEvent getLatestByTrackingNumber(String trackingNumber);

    List<TrackingEvent> getByStatus(String status);

    List<TrackingEvent> getByLocation(String location);

    List<TrackingEvent> getByDeliveryIdAndRange(Long deliveryId, LocalDateTime from, LocalDateTime to);

    long countByDeliveryId(Long deliveryId);

    long totalCount();

    void delete(Long id);

    DocumentRecord uploadDocument(DocumentRecord documentRecord);

    List<DocumentRecord> getDocumentsByTrackingNumber(String trackingNumber);

    DeliveryProofRecord getProof(Long deliveryId);

    DeliveryProofRecord saveProof(DeliveryProofRecord proofRecord);
}
