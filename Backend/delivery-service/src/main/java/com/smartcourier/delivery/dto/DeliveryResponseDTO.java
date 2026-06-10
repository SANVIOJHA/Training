package com.smartcourier.delivery.dto;

import lombok.Data;

/**
 * Response sent to client
 */
@Data
public class DeliveryResponseDTO {

    private Long id;
    private String trackingNumber;

    private String senderName;
    private String senderPhone;
    private String receiverName;
    private String receiverPhone;

    private String source;
    private String destination;

    private String status;

    private String assignedAgent;

    private Long currentHubId;
    private String currentHubName;

    private Double price;

    private String createdAt;
}