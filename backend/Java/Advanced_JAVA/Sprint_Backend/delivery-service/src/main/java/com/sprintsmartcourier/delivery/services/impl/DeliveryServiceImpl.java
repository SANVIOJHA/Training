package com.sprintsmartcourier.delivery.services.impl;

import com.sprintsmartcourier.delivery.dto.*;
import com.sprintsmartcourier.delivery.entity.Delivery;
import com.sprintsmartcourier.delivery.exception.DeliveryNotFoundException;
import com.sprintsmartcourier.delivery.mapper.DeliveryMapper;
import com.sprintsmartcourier.delivery.repository.DeliveryRepository;
import com.sprintsmartcourier.delivery.services.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository repository;

    @Override
    public DeliveryResponseDTO createDelivery(DeliveryRequestDTO request) {
        Delivery delivery = DeliveryMapper.toEntity(request);
        return DeliveryMapper.toDTO(repository.save(delivery));
    }

    @Override
    public DeliveryResponseDTO getByTrackingNumber(String trackingNumber) {
        Delivery delivery = repository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> new DeliveryNotFoundException("Delivery not found"));
        return DeliveryMapper.toDTO(delivery);
    }

    @Override
    public List<DeliveryResponseDTO> getAllDeliveries() {
        return repository.findAll()
                .stream()
                .map(DeliveryMapper::toDTO)
                .toList();
    }
}