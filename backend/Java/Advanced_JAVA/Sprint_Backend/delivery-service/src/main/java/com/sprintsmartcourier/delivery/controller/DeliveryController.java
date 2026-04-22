package com.sprintsmartcourier.delivery.controller;

import com.sprintsmartcourier.delivery.dto.*;
import com.sprintsmartcourier.delivery.services.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService service;

    // CREATE DELIVERY
    @PostMapping
    public ResponseEntity<DeliveryResponseDTO> create(@RequestBody DeliveryRequestDTO request) {
        return ResponseEntity.ok(service.createDelivery(request));
    }

    // GET DELIVERY BY TRACKING NUMBER
    @GetMapping("/{trackingNumber}")
    public ResponseEntity<DeliveryResponseDTO> get(@PathVariable String trackingNumber) {
        return ResponseEntity.ok(service.getByTrackingNumber(trackingNumber));
    }

    // GET ALL DELIVERIES
    @GetMapping
    public ResponseEntity<List<DeliveryResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAllDeliveries());
    }
}