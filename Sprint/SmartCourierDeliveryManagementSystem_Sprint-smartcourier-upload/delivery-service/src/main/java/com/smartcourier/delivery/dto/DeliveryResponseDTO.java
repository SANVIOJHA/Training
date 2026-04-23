package com.smartcourier.delivery.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * Response sent to client
 */
@Data
public class DeliveryResponseDTO {

    private Long id;
    private String trackingNumber;
    private String customerUsername;

    private String senderName;
    private String receiverName;
    private String senderAddress;
    private String receiverAddress;

    private String source;
    private String destination;
    private String serviceType;
    private String packageType;
    private Double packageWeight;
    private LocalDate pickupDate;
    private String packageDescription;
    private String notes;
    private String currentHub;

    private String status;

    private String assignedAgent;
    private Boolean exceptionResolved;
    private String exceptionNotes;

    private Double price;
}
