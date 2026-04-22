package com.example.vehiclerental.controller;

import com.example.vehiclerental.model.RentalPlan;
import com.example.vehiclerental.service.IRentalPlanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RentalPlanController {

    @Autowired
    private IRentalPlanService service;

    @PostMapping("/addRentalPlan")
    public ResponseEntity<RentalPlan> addRentalPlan(@RequestBody RentalPlan plan) {
        return ResponseEntity.ok(service.addRentalPlan(plan));
    }

    @GetMapping("/viewRentalPlanById/{planId}")
    public ResponseEntity<RentalPlan> viewRentalPlanById(@PathVariable String planId) {
        return ResponseEntity.ok(service.viewRentalPlanById(planId));
    }

    @GetMapping("/viewPlansByVehicleType/{vehicleType}")
    public ResponseEntity<List<RentalPlan>> viewPlansByVehicleType(@PathVariable String vehicleType) {
        return ResponseEntity.ok(service.viewPlansByVehicleType(vehicleType));
    }

    @GetMapping("/viewPlansByMileageAndDuration/{minMileage}/{durationDays}")
    public ResponseEntity<List<RentalPlan>> viewPlansByMileageAndDuration(
            @PathVariable int minMileage,
            @PathVariable int durationDays) {

        return ResponseEntity.ok(service.viewPlansByMileageAndDuration(minMileage, durationDays));
    }

    @GetMapping("/getPlanCountByCategory")
    public ResponseEntity<Map<String, Long>> getPlanCountByCategory() {
        return ResponseEntity.ok(service.getPlanCountByCategory());
    }
}