package com.smartcourier.tracking.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TrackingDTO {

    private Long deliveryId;
    private String status;
    private String location;
    private String description;
    private LocalDateTime timestamp;
}