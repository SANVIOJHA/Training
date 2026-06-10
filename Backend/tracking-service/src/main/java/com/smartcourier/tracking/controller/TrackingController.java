package com.smartcourier.tracking.controller;

import com.smartcourier.common.dto.ApiResponse;
import com.smartcourier.tracking.dto.TrackingDTO;
import com.smartcourier.tracking.entity.TrackingEvent;
import com.smartcourier.tracking.mapper.TrackingMapper;
import com.smartcourier.tracking.service.TrackingService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tracking")
public class TrackingController {

    private final TrackingService service;

    public TrackingController(TrackingService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TrackingDTO>> create(@Valid @RequestBody TrackingDTO dto) {
        TrackingEvent saved = service.addEvent(TrackingMapper.toEntity(dto));
        return ResponseEntity.ok(ApiResponse.success("Tracking event created", TrackingMapper.toDto(saved)));
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<ApiResponse<List<TrackingDTO>>> get(@PathVariable Long deliveryId) {
        List<TrackingDTO> events = service.getByDeliveryId(deliveryId).stream()
                .map(TrackingMapper::toDto).toList();
        return ResponseEntity.ok(ApiResponse.success("Tracking events retrieved", events));
    }

    @GetMapping("/{deliveryId}/latest")
    public ResponseEntity<ApiResponse<TrackingDTO>> latest(@PathVariable Long deliveryId) {
        return ResponseEntity.ok(ApiResponse.success("Latest event retrieved", TrackingMapper.toDto(service.getLatest(deliveryId))));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<TrackingDTO>>> byStatus(@PathVariable String status) {
        List<TrackingDTO> events = service.getByStatus(status).stream()
                .map(TrackingMapper::toDto).toList();
        return ResponseEntity.ok(ApiResponse.success("Tracking events retrieved", events));
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<ApiResponse<List<TrackingDTO>>> byLocation(@PathVariable String location) {
        List<TrackingDTO> events = service.getByLocation(location).stream()
                .map(TrackingMapper::toDto).toList();
        return ResponseEntity.ok(ApiResponse.success("Tracking events retrieved", events));
    }

    @GetMapping("/{deliveryId}/range")
    public ResponseEntity<ApiResponse<List<TrackingDTO>>> byRange(
            @PathVariable Long deliveryId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        List<TrackingDTO> events = service.getByDeliveryIdAndRange(deliveryId, from, to).stream()
                .map(TrackingMapper::toDto).toList();
        return ResponseEntity.ok(ApiResponse.success("Tracking events retrieved", events));
    }

    @GetMapping("/{deliveryId}/count")
    public ResponseEntity<ApiResponse<Long>> count(@PathVariable Long deliveryId) {
        return ResponseEntity.ok(ApiResponse.success("Count retrieved", service.countByDeliveryId(deliveryId)));
    }

    @GetMapping("/{deliveryId}/exists")
    public ResponseEntity<ApiResponse<Boolean>> exists(@PathVariable Long deliveryId) {
        return ResponseEntity.ok(ApiResponse.success("Existence check", service.countByDeliveryId(deliveryId) > 0));
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Long>> totalCount() {
        return ResponseEntity.ok(ApiResponse.success("Total count retrieved", service.totalCount()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Tracking event deleted"));
    }

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> health() {
        return ResponseEntity.ok(ApiResponse.success("Tracking Service Running"));
    }
}
