package com.smartcourier.admin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HubDTO {

    @NotBlank(message = "Hub name is required")
    private String name;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Status is required")
    private String status;
}
