package com.smartcourier.delivery.service;

import com.smartcourier.delivery.dto.DeliveryRequestDTO;
import com.smartcourier.delivery.dto.DeliveryResponseDTO;

import java.util.List;
import java.util.Map;

/**
 * Delivery service interface — returns DTOs only, never entities.
 */
public interface DeliveryService {

    DeliveryResponseDTO createDelivery(DeliveryRequestDTO dto, String username);

    List<DeliveryResponseDTO> getAllDeliveries();

    DeliveryResponseDTO getDeliveryById(Long id);

    List<DeliveryResponseDTO> getDeliveriesByCustomer(String username);

    DeliveryResponseDTO updateStatus(Long id, String status);

    DeliveryResponseDTO updateStatus(Long id, String status, String location, String description, Long currentHubId);

    DeliveryResponseDTO assignAgent(Long id, String agent);

    void deleteDelivery(Long id);

    List<DeliveryResponseDTO> getByStatus(String status);

    List<DeliveryResponseDTO> getByAgent(String agent);

    DeliveryResponseDTO getByTrackingNumber(String trackingNumber);

    List<DeliveryResponseDTO> getUnassigned();

    List<DeliveryResponseDTO> getByMinPrice(Double minPrice);

    long countByCustomer(String username);

    Map<String, Long> getStatusSummary();
}
