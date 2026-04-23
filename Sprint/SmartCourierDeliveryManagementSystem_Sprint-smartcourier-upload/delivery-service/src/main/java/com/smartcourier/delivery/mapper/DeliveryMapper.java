package com.smartcourier.delivery.mapper;

import com.smartcourier.delivery.dto.*;
import com.smartcourier.delivery.entity.Delivery;

public class DeliveryMapper {

    public static Delivery toEntity(DeliveryRequestDTO dto) {
        Delivery d = new Delivery();
        d.setSenderName(dto.getSenderName());
        d.setReceiverName(dto.getReceiverName());
        d.setSenderAddress(dto.getSenderAddress());
        d.setReceiverAddress(dto.getReceiverAddress());
        d.setSource(dto.getSource());
        d.setDestination(dto.getDestination());
        d.setServiceType(dto.getServiceType());
        d.setPackageType(dto.getPackageType());
        d.setPackageWeight(dto.getPackageWeight());
        d.setPickupDate(dto.getPickupDate());
        d.setPackageDescription(dto.getPackageDescription());
        d.setNotes(dto.getNotes());
        d.setCurrentHub(dto.getCurrentHub());
        d.setPrice(dto.getPrice());
        return d;
    }

    public static DeliveryResponseDTO toDTO(Delivery d) {
        DeliveryResponseDTO dto = new DeliveryResponseDTO();

        dto.setId(d.getId());
        dto.setTrackingNumber(d.getTrackingNumber());
        dto.setCustomerUsername(d.getCustomerUsername());

        dto.setSenderName(d.getSenderName());
        dto.setReceiverName(d.getReceiverName());
        dto.setSenderAddress(d.getSenderAddress());
        dto.setReceiverAddress(d.getReceiverAddress());

        dto.setSource(d.getSource());
        dto.setDestination(d.getDestination());
        dto.setServiceType(d.getServiceType());
        dto.setPackageType(d.getPackageType());
        dto.setPackageWeight(d.getPackageWeight());
        dto.setPickupDate(d.getPickupDate());
        dto.setPackageDescription(d.getPackageDescription());
        dto.setNotes(d.getNotes());
        dto.setCurrentHub(d.getCurrentHub());

        dto.setStatus(d.getStatus() != null ? d.getStatus().name() : null);

        dto.setAssignedAgent(d.getAssignedAgent());
        dto.setExceptionResolved(d.getExceptionResolved());
        dto.setExceptionNotes(d.getExceptionNotes());
        dto.setPrice(d.getPrice());

        return dto;
    }
}
