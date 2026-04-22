package com.smartcourier.tracking.service.impl;

import com.smartcourier.tracking.entity.TrackingEvent;
import com.smartcourier.tracking.exception.TrackingNotFoundException;
import com.smartcourier.tracking.repository.TrackingRepository;
import com.smartcourier.tracking.service.TrackingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrackingServiceImpl implements TrackingService {

    private final TrackingRepository repo;

    public TrackingServiceImpl(TrackingRepository repo) {
        this.repo = repo;
    }

    @Override
    public TrackingEvent addEvent(TrackingEvent event) {
        event.setTimestamp(LocalDateTime.now());
        return repo.save(event);
    }

    @Override
    public List<TrackingEvent> getByDeliveryId(Long deliveryId) {
        return repo.findByDeliveryId(deliveryId);
    }

    @Override
    public TrackingEvent getLatest(Long deliveryId) {
        return repo.findByDeliveryId(deliveryId)
                .stream()
                .reduce((first, second) -> second)
                .orElseThrow(() -> new TrackingNotFoundException("No tracking found"));
    }

    @Override
    public List<TrackingEvent> getByStatus(String status) {
        return repo.findByStatus(status);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}