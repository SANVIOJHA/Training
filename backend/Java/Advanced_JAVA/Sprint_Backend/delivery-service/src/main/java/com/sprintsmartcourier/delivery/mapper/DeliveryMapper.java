package com.sprintsmartcourier.delivery.mapper;

import com.sprintsmartcourier.delivery.dto.*;
import com.sprintsmartcourier.delivery.entity.*;
import com.sprintsmartcourier.delivery.entity.Package;

import java.time.LocalDateTime;
import java.util.UUID;

public class DeliveryMapper {

    public static Delivery toEntity(DeliveryRequestDTO dto) {
        return Delivery.builder()
                .trackingNumber(UUID.randomUUID().toString())
                .status(DeliveryStatus.BOOKED)
                .cost(dto.getCost())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .senderAddress(mapAddress(dto.getSenderAddress()))
                .receiverAddress(mapAddress(dto.getReceiverAddress()))
                .packageDetails(mapPackage(dto.getPackageDetails()))
                .build();
    }

    public static DeliveryResponseDTO toDTO(Delivery delivery) {
        return DeliveryResponseDTO.builder()
                .id(delivery.getId())
                .trackingNumber(delivery.getTrackingNumber())
                .status(delivery.getStatus())
                .cost(delivery.getCost())
                .build();
    }

    private static Address mapAddress(AddressDTO dto) {
        return Address.builder()
                .name(dto.getName())
                .phone(dto.getPhone())
                .street(dto.getStreet())
                .city(dto.getCity())
                .state(dto.getState())
                .country(dto.getCountry())
                .zipCode(dto.getZipCode())
                .build();
    }

    private static Package mapPackage(PackageDTO dto) {
        return Package.builder()
                .weight(dto.getWeight())
                .length(dto.getLength())
                .width(dto.getWidth())
                .height(dto.getHeight())
                .type(dto.getType())
                .description(dto.getDescription())
                .build();
    }
}