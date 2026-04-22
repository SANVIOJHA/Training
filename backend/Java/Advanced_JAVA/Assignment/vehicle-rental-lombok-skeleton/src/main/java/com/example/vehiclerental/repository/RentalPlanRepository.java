package com.example.vehiclerental.repository;

import com.example.vehiclerental.model.RentalPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentalPlanRepository extends JpaRepository<RentalPlan, String> {

    List<RentalPlan> findByVehicleTypeContainingIgnoreCase(String vehicleType);

    List<RentalPlan> findByMinMileageGreaterThanEqualAndRentalDurationDaysGreaterThanEqual(int minMileage, int rentalDurationDays);

    @Query("SELECT r.rentalCategory, COUNT(r) FROM RentalPlan r GROUP BY r.rentalCategory")
    List<Object[]> getPlanCountByCategory();
}