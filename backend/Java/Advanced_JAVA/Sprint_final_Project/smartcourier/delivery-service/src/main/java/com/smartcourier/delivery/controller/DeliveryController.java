package com.smartcourier.delivery.controller;

import com.smartcourier.delivery.dto.*;
import com.smartcourier.delivery.entity.Delivery;
import com.smartcourier.delivery.mapper.DeliveryMapper;
import com.smartcourier.delivery.service.DeliveryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * DeliveryController handles all REST APIs related to courier deliveries.
 * It acts as the entry point for client requests and delegates business logic to Service layer.
 */
@Tag(name = "Delivery APIs", description = "Operations related to courier deliveries")
@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryService service;


    public DeliveryController(DeliveryService service) {
        this.service = service;
    }

    // ================= USER-SPECIFIC =================

    /**
     * Get deliveries of logged-in user only
     * Username comes from API Gateway (secure)
     */
    @GetMapping("/my")
    public List<DeliveryResponseDTO> getMyDeliveries(
            @RequestHeader("X-User") String username) {

        return service.getByCustomer(username)
                .stream()
                .map(DeliveryMapper::toDTO)
                .toList();
    }

    // ================= CREATE =================

    /**
     * Create delivery
     * Username is injected by Gateway (NOT from request body)
     */
    @PostMapping
    public DeliveryResponseDTO create(
            @RequestBody DeliveryRequestDTO dto,
            @RequestHeader("X-User") String username) {

        Delivery delivery = DeliveryMapper.toEntity(dto);

        return DeliveryMapper.toDTO(
                service.createDelivery(delivery, username)
        );
    }

    // ================= ADMIN / GENERAL =================

    /**
     * Get all deliveries (Admin use)
     */
    @GetMapping
    public List<DeliveryResponseDTO> getAll() {
        return service.getAllDeliveries()
                .stream()
                .map(DeliveryMapper::toDTO)
                .toList();
    }

    /**
     * Get delivery by ID
     */
    @GetMapping("/{id}")
    public DeliveryResponseDTO getById(@PathVariable Long id) {
        return DeliveryMapper.toDTO(service.getDeliveryById(id));
    }

    /**
     * Update delivery details
     */
    @PutMapping("/{id}")
    public DeliveryResponseDTO update(
            @PathVariable Long id,
            @RequestBody DeliveryRequestDTO dto) {

        return DeliveryMapper.toDTO(
                service.updateDelivery(id, DeliveryMapper.toEntity(dto))
        );
    }

    /**
     * Delete delivery
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteDelivery(id);
        return "Delivery deleted successfully";
    }

    // ================= AGENT =================

    /**
     * Assign delivery agent
     */
    @PutMapping("/{id}/assign/{agent}")
    public DeliveryResponseDTO assignAgent(
            @PathVariable Long id,
            @PathVariable String agent) {

        return DeliveryMapper.toDTO(service.assignAgent(id, agent));
    }

    // ================= STATUS =================

    /**
     * Update status dynamically
     */
    @PutMapping("/{id}/status")
    public DeliveryResponseDTO updateStatus(
            @PathVariable Long id,
            @RequestBody StatusUpdateDTO dto) {

        return DeliveryMapper.toDTO(
                service.updateStatus(id, dto.getStatus())
        );
    }

    /**
     * Shortcut APIs for status transitions
     */
    @PutMapping("/{id}/cancel")
    public DeliveryResponseDTO cancel(@PathVariable Long id) {
        return DeliveryMapper.toDTO(service.updateStatus(id, "CANCELLED"));
    }

    @PutMapping("/{id}/ship")
    public DeliveryResponseDTO ship(@PathVariable Long id) {
        return DeliveryMapper.toDTO(service.updateStatus(id, "SHIPPED"));
    }

    @PutMapping("/{id}/deliver")
    public DeliveryResponseDTO deliver(@PathVariable Long id) {
        return DeliveryMapper.toDTO(service.updateStatus(id, "DELIVERED"));
    }

    // ================= FILTER =================

    @GetMapping("/status/{status}")
    public List<DeliveryResponseDTO> getByStatus(@PathVariable String status) {
        return service.getByStatus(status)
                .stream()
                .map(DeliveryMapper::toDTO)
                .toList();
    }

    @GetMapping("/agent/{agent}")
    public List<DeliveryResponseDTO> getByAgent(@PathVariable String agent) {
        return service.getByAgent(agent)
                .stream()
                .map(DeliveryMapper::toDTO)
                .toList();
    }

    // ================= ANALYTICS =================

    @GetMapping("/count")
    public int count() {
        return service.getAllDeliveries().size();
    }

    @GetMapping("/health")
    public String health() {
        return "Delivery Service is running";
    }
}