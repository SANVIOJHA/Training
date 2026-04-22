package com.logistic.ecommerceOrder.controller;

import com.logistic.ecommerceOrder.dto.ShipmentRequestDto;
import com.logistic.ecommerceOrder.entity.Shipment;
import com.logistic.ecommerceOrder.service.ShipmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipments")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService service;

    @PostMapping
    public ResponseEntity<Shipment> createShipment(
            @Valid @RequestBody ShipmentRequestDto dto) {

        return new ResponseEntity<>(service.createShipment(dto),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Shipment>> getAllShipments() {
        return ResponseEntity.ok(service.getAllShipments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getShipment(@PathVariable Long id) {
        return ResponseEntity.ok(service.getShipmentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shipment> updateShipment(
            @PathVariable Long id,
            @Valid @RequestBody ShipmentRequestDto dto) {

        return ResponseEntity.ok(service.updateShipment(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShipment(@PathVariable Long id) {
        service.deleteShipment(id);
        return ResponseEntity.ok("Shipment deleted successfully");
    }
}