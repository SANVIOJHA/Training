package com.logistic.ecommerceOrder.dto;

import com.logistic.ecommerceOrder.entity.ShipmentStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipmentRequestDto {

    @NotBlank
    @Size(min = 3, max = 50)
    private String trackingNumber;

    @NotNull
    private ShipmentStatus status;

    @Email
    private String customerEmail;

    @NotNull
    private Long warehouseId;
}