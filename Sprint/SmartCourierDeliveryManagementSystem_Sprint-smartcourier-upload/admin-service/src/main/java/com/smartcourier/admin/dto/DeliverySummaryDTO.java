package com.smartcourier.admin.dto;

import lombok.Data;

@Data
public class DeliverySummaryDTO {

    private Long id;
    private String trackingNumber;
    private String customerUsername;
    private String status;
    private String assignedAgent;
    private String currentHub;
    private Boolean exceptionResolved;
    private String exceptionNotes;
    private Double price;
}
