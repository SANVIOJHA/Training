package com.smartcourier.admin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeliveryResolutionRequest {

    @NotBlank(message = "Resolution status is required")
    private String resolutionStatus;

    @NotBlank(message = "Notes are required")
    private String notes;

    private String hub;
}
