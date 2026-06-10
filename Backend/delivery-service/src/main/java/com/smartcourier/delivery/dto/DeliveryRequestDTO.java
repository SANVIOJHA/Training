package com.smartcourier.delivery.dto;

import com.smartcourier.common.validation.ValidPhoneNumber;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DeliveryRequestDTO {

    @NotBlank(message = "Sender name is required")
    @Size(min = 2, max = 100, message = "Sender name must be between 2 and 100 characters")
    private String senderName;

    @ValidPhoneNumber
    private String senderPhone;

    @NotBlank(message = "Receiver name is required")
    @Size(min = 2, max = 100, message = "Receiver name must be between 2 and 100 characters")
    private String receiverName;

    @NotBlank(message = "Receiver phone is required")
    @ValidPhoneNumber
    private String receiverPhone;

    @NotBlank(message = "Source is required")
    @Size(min = 2, max = 500, message = "Source must be between 2 and 500 characters")
    private String source;

    @NotBlank(message = "Destination is required")
    @Size(min = 2, max = 500, message = "Destination must be between 2 and 500 characters")
    private String destination;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than zero")
    @DecimalMax(value = "100000.00", message = "Price cannot exceed 100,000")
    private Double price;

    @DecimalMin(value = "0.01", message = "Weight must be greater than zero")
    @DecimalMax(value = "500.0", message = "Weight cannot exceed 500 kg")
    private Double weight;
}
