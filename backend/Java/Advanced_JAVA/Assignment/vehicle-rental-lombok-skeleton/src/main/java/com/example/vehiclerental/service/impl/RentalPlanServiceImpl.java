package com.example.vehiclerental.service.impl;

import com.example.vehiclerental.exception.InvalidRentalPlanException;
import com.example.vehiclerental.model.RentalPlan;
import com.example.vehiclerental.repository.RentalPlanRepository;
import com.example.vehiclerental.service.IRentalPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RentalPlanServiceImpl implements IRentalPlanService {

    @Autowired
    private RentalPlanRepository repository;

    @Override
    public RentalPlan addRentalPlan(RentalPlan plan) {
        return repository.save(plan);
    }

    @Override
    public RentalPlan viewRentalPlanById(String planId) {
        return repository.findById(planId)
                .orElseThrow(() -> new InvalidRentalPlanException("Rental Plan not found with id: " + planId));
    }

    @Override
    public List<RentalPlan> viewPlansByVehicleType(String vehicleType) {
        return repository.findByVehicleTypeContainingIgnoreCase(vehicleType);
    }

    @Override
    public List<RentalPlan> viewPlansByMileageAndDuration(int minMileage, int durationDays) {
        return repository.findByMinMileageGreaterThanEqualAndRentalDurationDaysGreaterThanEqual(minMileage, durationDays);
    }

    @Override
    public Map<String, Long> getPlanCountByCategory() {

        List<Object[]> list = repository.getPlanCountByCategory();
        Map<String, Long> result = new HashMap<>();

        for(Object[] obj : list){
            result.put((String)obj[0], (Long)obj[1]);
        }

        return result;
    }
}