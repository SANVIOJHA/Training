package com.sprintsmartcourier.delivery.dto;

import com.sprintsmartcourier.delivery.entity.DeliveryStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryResponseDTO {

    private Long id;
    private String trackingNumber;
    private DeliveryStatus status;
    private Double cost;
}