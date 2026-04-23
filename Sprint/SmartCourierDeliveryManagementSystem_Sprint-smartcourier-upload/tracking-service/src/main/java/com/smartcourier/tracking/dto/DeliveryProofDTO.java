package com.smartcourier.tracking.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryProofDTO {

    private Long id;
    private Long deliveryId;
    private String trackingNumber;
    private String recipientName;
    private String deliveredLocation;
    private String confirmationMessage;
    private LocalDateTime deliveredAt;
}
