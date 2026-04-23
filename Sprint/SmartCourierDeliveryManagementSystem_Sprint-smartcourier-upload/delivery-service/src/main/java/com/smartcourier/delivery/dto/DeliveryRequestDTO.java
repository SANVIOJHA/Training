package com.smartcourier.delivery.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DeliveryRequestDTO {
    @NotBlank(message = "Sender name is required")
    private String senderName;

    @NotBlank(message = "Receiver name is required")
    private String receiverName;

    @NotBlank(message = "Source is required")
    private String source;

    @NotBlank(message = "Destination is required")
    private String destination;

    @NotBlank(message = "Sender address is required")
    private String senderAddress;

    @NotBlank(message = "Receiver address is required")
    private String receiverAddress;

    @NotBlank(message = "Service type is required")
    private String serviceType;

    @NotBlank(message = "Package type is required")
    private String packageType;

    @NotNull(message = "Package weight is required")
    @DecimalMin(value = "0.1", inclusive = true, message = "Package weight must be positive")
    private Double packageWeight;

    @NotNull(message = "Pickup date is required")
    private LocalDate pickupDate;

    private String packageDescription;
    private String notes;
    private String currentHub;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private Double price;
}
