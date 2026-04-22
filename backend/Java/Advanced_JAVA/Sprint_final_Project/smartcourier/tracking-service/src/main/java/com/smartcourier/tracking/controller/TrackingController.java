package com.smartcourier.tracking.controller;

import com.smartcourier.tracking.entity.TrackingEvent;
import com.smartcourier.tracking.service.TrackingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tracking")
public class TrackingController {

    private final TrackingService service;

    public TrackingController(TrackingService service) {
        this.service = service;
    }

    @PostMapping
    public TrackingEvent create(@RequestBody TrackingEvent event) {
        return service.addEvent(event);
    }

    @GetMapping("/{deliveryId}")
    public List<TrackingEvent> get(@PathVariable Long deliveryId) {
        return service.getByDeliveryId(deliveryId);
    }

    @GetMapping("/{deliveryId}/latest")
    public TrackingEvent latest(@PathVariable Long deliveryId) {
        return service.getLatest(deliveryId);
    }

    @GetMapping("/status/{status}")
    public List<TrackingEvent> byStatus(@PathVariable String status) {
        return service.getByStatus(status);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted";
    }

    @GetMapping("/health")
    public String health() {
        return "Tracking Service Running";
    }
}