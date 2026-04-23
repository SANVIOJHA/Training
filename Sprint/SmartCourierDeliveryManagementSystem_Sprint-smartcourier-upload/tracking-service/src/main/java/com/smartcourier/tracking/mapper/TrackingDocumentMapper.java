package com.smartcourier.tracking.mapper;

import com.smartcourier.tracking.dto.DeliveryProofDTO;
import com.smartcourier.tracking.dto.DocumentResponseDTO;
import com.smartcourier.tracking.dto.DocumentUploadDTO;
import com.smartcourier.tracking.entity.DeliveryProofRecord;
import com.smartcourier.tracking.entity.DocumentRecord;

import java.time.LocalDateTime;

public final class TrackingDocumentMapper {

    private TrackingDocumentMapper() {
    }

    public static DocumentRecord toEntity(DocumentUploadDTO dto) {
        DocumentRecord entity = new DocumentRecord();
        entity.setDeliveryId(dto.getDeliveryId());
        entity.setTrackingNumber(dto.getTrackingNumber());
        entity.setDocumentType(dto.getDocumentType());
        entity.setFileName(dto.getFileName());
        entity.setContentType(dto.getContentType());
        entity.setBase64Content(dto.getBase64Content());
        entity.setUploadedBy(dto.getUploadedBy());
        entity.setUploadedAt(LocalDateTime.now());
        return entity;
    }

    public static DocumentResponseDTO toDto(DocumentRecord entity) {
        DocumentResponseDTO dto = new DocumentResponseDTO();
        dto.setId(entity.getId());
        dto.setDeliveryId(entity.getDeliveryId());
        dto.setTrackingNumber(entity.getTrackingNumber());
        dto.setDocumentType(entity.getDocumentType());
        dto.setFileName(entity.getFileName());
        dto.setContentType(entity.getContentType());
        dto.setBase64Content(entity.getBase64Content());
        dto.setUploadedBy(entity.getUploadedBy());
        dto.setUploadedAt(entity.getUploadedAt());
        return dto;
    }

    public static DeliveryProofDTO toDto(DeliveryProofRecord entity) {
        DeliveryProofDTO dto = new DeliveryProofDTO();
        dto.setId(entity.getId());
        dto.setDeliveryId(entity.getDeliveryId());
        dto.setTrackingNumber(entity.getTrackingNumber());
        dto.setRecipientName(entity.getRecipientName());
        dto.setDeliveredLocation(entity.getDeliveredLocation());
        dto.setConfirmationMessage(entity.getConfirmationMessage());
        dto.setDeliveredAt(entity.getDeliveredAt());
        return dto;
    }
}
