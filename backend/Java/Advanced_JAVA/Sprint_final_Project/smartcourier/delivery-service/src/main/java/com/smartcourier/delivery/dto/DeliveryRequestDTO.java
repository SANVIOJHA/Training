package com.smartcourier.delivery.dto;

import lombok.Data;

@Data
public class DeliveryRequestDTO {
    private String senderName;
    private String receiverName;
    private String source;
    private String destination;
    private Double price;
}