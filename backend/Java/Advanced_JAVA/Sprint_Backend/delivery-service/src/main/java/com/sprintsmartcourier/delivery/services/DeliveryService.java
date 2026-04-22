package com.sprintsmartcourier.delivery.services;

import com.sprintsmartcourier.delivery.dto.*;

import java.util.List;

public interface DeliveryService {

    DeliveryResponseDTO createDelivery(DeliveryRequestDTO request);

    DeliveryResponseDTO getByTrackingNumber(String trackingNumber);

    List<DeliveryResponseDTO> getAllDeliveries();
}