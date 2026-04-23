package com.smartcourier.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ServiceCatalogController {

    @GetMapping("/gateway/services")
    public List<Map<String, String>> services() {
        return List.of(
                Map.of("id", "domestic", "name", "Domestic Courier", "description", "Reliable inter-city parcel delivery"),
                Map.of("id", "express", "name", "Express Courier", "description", "Priority same-day and next-day shipments"),
                Map.of("id", "international", "name", "International Courier", "description", "Cross-border parcel handling with customs-ready support")
        );
    }
}
