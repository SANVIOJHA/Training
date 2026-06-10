package com.smartcourier.delivery.service.impl;

import com.smartcourier.delivery.dto.HubDTO;
import com.smartcourier.delivery.entity.Hub;
import com.smartcourier.common.exception.BusinessValidationException;
import com.smartcourier.common.exception.ResourceNotFoundException;
import com.smartcourier.delivery.repository.HubRepository;
import com.smartcourier.delivery.service.HubService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HubServiceImpl implements HubService {

    private final HubRepository repo;
    private final com.smartcourier.delivery.repository.DeliveryRepository deliveryRepository;

    public HubServiceImpl(HubRepository repo, com.smartcourier.delivery.repository.DeliveryRepository deliveryRepository) {
        this.repo = repo;
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public HubDTO createHub(HubDTO dto) {
        if (repo.findByCode(dto.getCode()).isPresent()) {
            throw new BusinessValidationException("Hub code already exists");
        }
        Hub hub = mapToEntity(dto);
        return mapToDto(repo.save(hub));
    }

    @Override
    public HubDTO updateHub(Long id, HubDTO dto) {
        Hub hub = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hub not found"));
        
        repo.findByCode(dto.getCode()).ifPresent(existing -> {
            if (!existing.getId().equals(id)) {
                throw new BusinessValidationException("Hub code already in use");
            }
        });

        hub.setName(dto.getName());
        hub.setCode(dto.getCode());
        hub.setCity(dto.getCity());
        hub.setState(dto.getState());
        hub.setAddress(dto.getAddress());
        hub.setContactNumber(dto.getContactNumber());
        
        return mapToDto(repo.save(hub));
    }

    @Override
    public void deleteHub(Long id) {
        Hub hub = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hub not found"));
        if (deliveryRepository.countByCurrentHubId(id) > 0) {
            throw new BusinessValidationException("Hub cannot be deleted while deliveries are assigned to it");
        }
        repo.delete(hub);
    }

    @Override
    public HubDTO getHub(Long id) {
        return repo.findById(id).map(this::mapToDto).orElseThrow(() -> new ResourceNotFoundException("Hub not found"));
    }

    @Override
    public List<HubDTO> getAllHubs() {
        return repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private Hub mapToEntity(HubDTO dto) {
        Hub hub = new Hub();
        hub.setName(dto.getName());
        hub.setCode(dto.getCode());
        hub.setCity(dto.getCity());
        hub.setState(dto.getState());
        hub.setAddress(dto.getAddress());
        hub.setContactNumber(dto.getContactNumber());
        return hub;
    }

    private HubDTO mapToDto(Hub hub) {
        HubDTO dto = new HubDTO();
        dto.setId(hub.getId());
        dto.setName(hub.getName());
        dto.setCode(hub.getCode());
        dto.setCity(hub.getCity());
        dto.setState(hub.getState());
        dto.setAddress(hub.getAddress());
        dto.setContactNumber(hub.getContactNumber());
        return dto;
    }
}
