package com.example.vehiclerental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalPlan {

    @Id
    @NotBlank(message="Provide value for plan Id")
    private String planId;

    @NotBlank(message="Provide value for plan name")
    private String planName;

    @NotBlank(message="Provide value for vehicle type")
    private String vehicleType;

    @Min(value=50, message="Minimum mileage must be between 50 and 500")
    @Max(value=500, message="Minimum mileage must be between 50 and 500")
    private int minMileage;

    @Min(value=1, message="Rental duration must be at least 1 day")
    private int rentalDurationDays;

    @Positive(message="Daily rate must be a positive value")
    private double dailyRate;

    @NotBlank(message="Provide value for rental category")
    private String rentalCategory;

    private boolean insuranceIncluded;
}