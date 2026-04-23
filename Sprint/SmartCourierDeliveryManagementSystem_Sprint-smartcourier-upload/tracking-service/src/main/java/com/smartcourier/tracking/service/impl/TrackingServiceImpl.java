package com.smartcourier.tracking.service.impl;

import com.smartcourier.tracking.entity.DeliveryProofRecord;
import com.smartcourier.tracking.entity.DocumentRecord;
import com.smartcourier.tracking.entity.TrackingEvent;
import com.smartcourier.tracking.exception.TrackingNotFoundException;
import com.smartcourier.tracking.repository.DeliveryProofRepository;
import com.smartcourier.tracking.repository.DocumentRecordRepository;
import com.smartcourier.tracking.repository.TrackingRepository;
import com.smartcourier.tracking.service.TrackingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class TrackingServiceImpl implements TrackingService {

    private final TrackingRepository repo;
    private final DocumentRecordRepository documentRecordRepository;
    private final DeliveryProofRepository deliveryProofRepository;

    public TrackingServiceImpl(
            TrackingRepository repo,
            DocumentRecordRepository documentRecordRepository,
            DeliveryProofRepository deliveryProofRepository) {
        this.repo = repo;
        this.documentRecordRepository = documentRecordRepository;
        this.deliveryProofRepository = deliveryProofRepository;
    }

    @Override
    public TrackingEvent addEvent(TrackingEvent event) {
        if (event.getTimestamp() == null) {
            event.setTimestamp(LocalDateTime.now());
        }
        return repo.save(event);
    }

    @Override
    public List<TrackingEvent> getByDeliveryId(Long deliveryId) {
        return repo.findByDeliveryId(deliveryId);
    }

    @Override
    public List<TrackingEvent> getByTrackingNumber(String trackingNumber) {
        return repo.findByTrackingNumberOrderByTimestampAsc(trackingNumber);
    }

    @Override
    public TrackingEvent getLatest(Long deliveryId) {
        return repo.findByDeliveryId(deliveryId).stream()
                .max(Comparator.comparing(TrackingEvent::getTimestamp))
                .orElseThrow(() -> new TrackingNotFoundException("No tracking events found for delivery id: " + deliveryId));
    }

    @Override
    public TrackingEvent getLatestByTrackingNumber(String trackingNumber) {
        return repo.findByTrackingNumberOrderByTimestampAsc(trackingNumber).stream()
                .max(Comparator.comparing(TrackingEvent::getTimestamp))
                .orElseThrow(() -> new TrackingNotFoundException("No tracking events found for tracking number: " + trackingNumber));
    }

    @Override
    public List<TrackingEvent> getByStatus(String status) {
        return repo.findByStatus(status);
    }

    @Override
    public List<TrackingEvent> getByLocation(String location) {
        return repo.findByLocationContainingIgnoreCase(location);
    }

    @Override
    public List<TrackingEvent> getByDeliveryIdAndRange(Long deliveryId, LocalDateTime from, LocalDateTime to) {
        return repo.findByDeliveryIdAndTimestampBetween(deliveryId, from, to);
    }

    @Override
    public long countByDeliveryId(Long deliveryId) {
        return repo.countByDeliveryId(deliveryId);
    }

    @Override
    public long totalCount() {
        return repo.count();
    }

    @Override
    public void delete(Long id) {
        TrackingEvent event = repo.findById(id)
                .orElseThrow(() -> new TrackingNotFoundException("Tracking event not found for id: " + id));
        repo.delete(event);
    }

    @Override
    public DocumentRecord uploadDocument(DocumentRecord documentRecord) {
        return documentRecordRepository.save(documentRecord);
    }

    @Override
    public List<DocumentRecord> getDocumentsByTrackingNumber(String trackingNumber) {
        return documentRecordRepository.findByTrackingNumberOrderByUploadedAtDesc(trackingNumber);
    }

    @Override
    public DeliveryProofRecord getProof(Long deliveryId) {
        return deliveryProofRepository.findByDeliveryId(deliveryId)
                .orElseThrow(() -> new TrackingNotFoundException("No delivery proof found for delivery id: " + deliveryId));
    }

    @Override
    public DeliveryProofRecord saveProof(DeliveryProofRecord proofRecord) {
        return deliveryProofRepository.save(proofRecord);
    }
}
