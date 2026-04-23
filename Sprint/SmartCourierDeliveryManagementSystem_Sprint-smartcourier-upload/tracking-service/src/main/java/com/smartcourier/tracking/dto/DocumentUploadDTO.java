package com.smartcourier.tracking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DocumentUploadDTO {

    @NotNull(message = "Delivery id is required")
    private Long deliveryId;

    @NotBlank(message = "Tracking number is required")
    private String trackingNumber;

    @NotBlank(message = "Document type is required")
    private String documentType;

    @NotBlank(message = "File name is required")
    private String fileName;

    @NotBlank(message = "Content type is required")
    private String contentType;

    @NotBlank(message = "Document content is required")
    private String base64Content;

    private String uploadedBy;
}
