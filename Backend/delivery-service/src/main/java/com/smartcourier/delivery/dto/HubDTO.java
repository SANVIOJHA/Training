package com.smartcourier.delivery.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HubDTO {
    private Long id;
    
    @NotBlank(message = "Hub name is required")
    private String name;
    
    @NotBlank(message = "Hub code is required")
    private String code;
    
    @NotBlank(message = "City is required")
    private String city;
    
    @NotBlank(message = "State is required")
    private String state;
    
    private String address;
    private String contactNumber;
}
