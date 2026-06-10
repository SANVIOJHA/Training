package com.smartcourier.delivery.controller;

import com.smartcourier.common.dto.ApiResponse;
import com.smartcourier.delivery.dto.HubDTO;
import com.smartcourier.delivery.service.HubService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries/hubs")
public class HubController {

    private final HubService service;

    public HubController(HubService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<HubDTO>> create(@Valid @RequestBody HubDTO dto) {
        return ResponseEntity.ok(ApiResponse.success("Hub created successfully", service.createHub(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<HubDTO>> update(@PathVariable Long id, @Valid @RequestBody HubDTO dto) {
        return ResponseEntity.ok(ApiResponse.success("Hub updated successfully", service.updateHub(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        service.deleteHub(id);
        return ResponseEntity.ok(ApiResponse.success("Hub deleted successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<HubDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Hub retrieved", service.getHub(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<HubDTO>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success("Hubs retrieved", service.getAllHubs()));
    }
}
