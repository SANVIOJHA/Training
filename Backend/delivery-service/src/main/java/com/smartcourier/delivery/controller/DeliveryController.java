package com.smartcourier.delivery.controller;

import com.smartcourier.common.dto.ApiResponse;
import com.smartcourier.delivery.dto.AgentAssignDTO;
import com.smartcourier.delivery.dto.DeliveryRequestDTO;
import com.smartcourier.delivery.dto.DeliveryResponseDTO;
import com.smartcourier.delivery.dto.StatusUpdateDTO;
import com.smartcourier.delivery.service.DeliveryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryService service;

    public DeliveryController(DeliveryService service) {
        this.service = service;
    }

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<DeliveryResponseDTO>>> getMyDeliveries(@RequestHeader("X-User") String username) {
        return ResponseEntity.ok(ApiResponse.success("Deliveries retrieved", service.getDeliveriesByCustomer(username)));
    }

    @GetMapping("/my/count")
    public ResponseEntity<ApiResponse<Long>> getMyDeliveryCount(@RequestHeader("X-User") String username) {
        return ResponseEntity.ok(ApiResponse.success("Delivery count retrieved", service.countByCustomer(username)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DeliveryResponseDTO>> create(
            @Valid @RequestBody DeliveryRequestDTO dto,
            @RequestHeader("X-User") String username) {
        return ResponseEntity.ok(ApiResponse.success("Delivery created", service.createDelivery(dto, username)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DeliveryResponseDTO>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success("Deliveries retrieved", service.getAllDeliveries()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DeliveryResponseDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Delivery retrieved", service.getDeliveryById(id)));
    }

    @GetMapping("/tracking/{trackingNumber}")
    public ResponseEntity<ApiResponse<DeliveryResponseDTO>> getByTrackingNumber(@PathVariable String trackingNumber) {
        return ResponseEntity.ok(ApiResponse.success("Delivery retrieved", service.getByTrackingNumber(trackingNumber)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        service.deleteDelivery(id);
        return ResponseEntity.ok(ApiResponse.success("Delivery deleted successfully"));
    }

    @PutMapping("/{id}/assign/{agent}")
    public ResponseEntity<ApiResponse<DeliveryResponseDTO>> assignAgent(@PathVariable Long id, @PathVariable String agent) {
        return ResponseEntity.ok(ApiResponse.success("Agent assigned", service.assignAgent(id, agent)));
    }

    @PutMapping("/{id}/assign")
    public ResponseEntity<ApiResponse<DeliveryResponseDTO>> assignAgentWithBody(@PathVariable Long id, @Valid @RequestBody AgentAssignDTO dto) {
        return ResponseEntity.ok(ApiResponse.success("Agent assigned", service.assignAgent(id, dto.getAgent())));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<DeliveryResponseDTO>> updateStatus(@PathVariable Long id, @Valid @RequestBody StatusUpdateDTO dto) {
        return ResponseEntity.ok(ApiResponse.success("Status updated",
                service.updateStatus(id, dto.getStatus(), dto.getLocation(), dto.getDescription(), dto.getCurrentHubId())));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<DeliveryResponseDTO>> cancel(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Delivery cancelled", service.updateStatus(id, "CANCELLED")));
    }

    @PutMapping("/{id}/ship")
    public ResponseEntity<ApiResponse<DeliveryResponseDTO>> ship(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Delivery shipped", service.updateStatus(id, "SHIPPED")));
    }

    @PutMapping("/{id}/deliver")
    public ResponseEntity<ApiResponse<DeliveryResponseDTO>> deliver(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Delivery completed", service.updateStatus(id, "DELIVERED")));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<DeliveryResponseDTO>>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(ApiResponse.success("Deliveries retrieved", service.getByStatus(status)));
    }

    @GetMapping("/agent/{agent}")
    public ResponseEntity<ApiResponse<List<DeliveryResponseDTO>>> getByAgent(@PathVariable String agent) {
        return ResponseEntity.ok(ApiResponse.success("Deliveries retrieved", service.getByAgent(agent)));
    }

    @GetMapping("/unassigned")
    public ResponseEntity<ApiResponse<List<DeliveryResponseDTO>>> getUnassigned() {
        return ResponseEntity.ok(ApiResponse.success("Unassigned deliveries retrieved", service.getUnassigned()));
    }

    @GetMapping("/price/above/{amount}")
    public ResponseEntity<ApiResponse<List<DeliveryResponseDTO>>> getByMinPrice(@PathVariable Double amount) {
        return ResponseEntity.ok(ApiResponse.success("Deliveries retrieved", service.getByMinPrice(amount)));
    }

    @GetMapping("/summary/status")
    public ResponseEntity<ApiResponse<Map<String, Long>>> statusSummary() {
        return ResponseEntity.ok(ApiResponse.success("Status summary retrieved", service.getStatusSummary()));
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Integer>> count() {
        return ResponseEntity.ok(ApiResponse.success("Count retrieved", service.getAllDeliveries().size()));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<DeliveryResponseDTO>>> search(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String agent) {
        List<DeliveryResponseDTO> results;
        if (status != null && !status.isBlank()) {
            results = service.getByStatus(status);
        } else if (agent != null && !agent.isBlank()) {
            results = service.getByAgent(agent);
        } else {
            results = service.getAllDeliveries();
        }
        return ResponseEntity.ok(ApiResponse.success("Search results", results));
    }

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> health() {
        return ResponseEntity.ok(ApiResponse.success("Delivery Service is running"));
    }
}
