package com.smartcourier.delivery.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeliveryResolutionDTO {

    @NotBlank(message = "Resolution status is required")
    private String resolutionStatus;

    @NotBlank(message = "Resolution notes are required")
    private String notes;

    private String hub;
}
