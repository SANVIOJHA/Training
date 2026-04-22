
package com.example.vehiclerental.service;

import com.example.vehiclerental.model.RentalPlan;
import java.util.List;
import java.util.Map;

public interface IRentalPlanService {
    RentalPlan addRentalPlan(RentalPlan plan);
    RentalPlan viewRentalPlanById(String planId);
    List<RentalPlan> viewPlansByVehicleType(String vehicleType);
    List<RentalPlan> viewPlansByMileageAndDuration(int minMileage, int durationDays);
    Map<String, Long> getPlanCountByCategory();
}
