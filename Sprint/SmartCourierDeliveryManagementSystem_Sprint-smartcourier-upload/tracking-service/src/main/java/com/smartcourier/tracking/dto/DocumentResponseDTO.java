package com.smartcourier.tracking.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DocumentResponseDTO {

    private Long id;
    private Long deliveryId;
    private String trackingNumber;
    private String documentType;
    private String fileName;
    private String contentType;
    private String base64Content;
    private String uploadedBy;
    private LocalDateTime uploadedAt;
}
