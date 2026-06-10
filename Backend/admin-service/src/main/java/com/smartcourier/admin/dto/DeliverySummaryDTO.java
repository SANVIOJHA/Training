package com.smartcourier.admin.dto;

import lombok.Data;

@Data
public class DeliverySummaryDTO {
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
}
