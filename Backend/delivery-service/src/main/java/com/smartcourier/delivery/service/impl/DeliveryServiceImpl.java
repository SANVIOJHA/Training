package com.smartcourier.delivery.service.impl;

import com.smartcourier.common.constant.SmartCourierConstants;
import com.smartcourier.common.event.DeliveryStatusEvent;
import com.smartcourier.common.exception.BusinessValidationException;
import com.smartcourier.common.exception.ResourceNotFoundException;
import com.smartcourier.delivery.dto.DeliveryRequestDTO;
import com.smartcourier.delivery.dto.DeliveryResponseDTO;
import com.smartcourier.delivery.entity.Delivery;
import com.smartcourier.delivery.enums.DeliveryStatus;
import com.smartcourier.delivery.mapper.DeliveryMapper;
import com.smartcourier.delivery.messaging.DeliveryEventPublisher;
import com.smartcourier.delivery.repository.DeliveryRepository;
import com.smartcourier.delivery.service.DeliveryService;
import com.smartcourier.delivery.strategy.PricingStrategy;
import com.smartcourier.delivery.strategy.PricingStrategyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private static final Logger log = LoggerFactory.getLogger(DeliveryServiceImpl.class);
    private static final Map<DeliveryStatus, Set<DeliveryStatus>> ALLOWED_TRANSITIONS = buildAllowedTransitions();

    private final DeliveryRepository repo;
    private final DeliveryMapper deliveryMapper;
    private final DeliveryEventPublisher eventPublisher;
    private final PricingStrategyFactory pricingStrategyFactory;
    private final com.smartcourier.delivery.repository.HubRepository hubRepository;

    public DeliveryServiceImpl(DeliveryRepository repo, DeliveryMapper deliveryMapper,
                               DeliveryEventPublisher eventPublisher,
                               PricingStrategyFactory pricingStrategyFactory,
                               com.smartcourier.delivery.repository.HubRepository hubRepository) {
        this.repo = repo;
        this.deliveryMapper = deliveryMapper;
        this.eventPublisher = eventPublisher;
        this.pricingStrategyFactory = pricingStrategyFactory;
        this.hubRepository = hubRepository;
    }

    @Override
    public DeliveryResponseDTO createDelivery(DeliveryRequestDTO dto, String customerUsername) {
        validateSourceNotEqualToDestination(dto);
        checkDuplicateBooking(dto, customerUsername);
        validatePriceSanity(dto);

        Delivery delivery = deliveryMapper.toEntity(dto);
        //traking number
        delivery.setTrackingNumber(SmartCourierConstants.TRACKING_PREFIX + UUID.randomUUID());
        delivery.setCustomerUsername(customerUsername);
        delivery.setStatus(DeliveryStatus.PENDING_DISPATCH);

        // Apply pricing strategy
        try {
            PricingStrategy strategy = pricingStrategyFactory.getStrategy(dto.getSource(), dto.getDestination());
            double calculatedPrice = strategy.calculatePrice(dto.getWeight() != null ? dto.getWeight() : 1.0);
            delivery.setPrice(calculatedPrice);
        } catch (Exception e) {
            log.warn("Pricing strategy failed, using client-provided price: {}", e.getMessage());
            delivery.setPrice(dto.getPrice());
        }

        Delivery saved = repo.save(delivery);
        log.info("Delivery created: {} for user {}", saved.getTrackingNumber(), customerUsername);
        publishStatusEvent(saved, "Delivery booked", saved.getSource());
        return deliveryMapper.toDto(saved);
    }

    @Override
    public List<DeliveryResponseDTO> getAllDeliveries() {
        return deliveryMapper.toDtoList(repo.findAll());
    }

    @Override
    public DeliveryResponseDTO getDeliveryById(Long id) {
        return deliveryMapper.toDto(findDeliveryOrThrow(id));
    }

    @Override
    public List<DeliveryResponseDTO> getDeliveriesByCustomer(String username) {
        return deliveryMapper.toDtoList(repo.findByCustomerUsername(username));
    }

    @Override
    public DeliveryResponseDTO updateStatus(Long id, String status) {
        return updateStatus(id, status, null, null, null);
    }

    public DeliveryResponseDTO updateStatus(Long id, String status, String location, String description, Long currentHubId) {
        Delivery delivery = findDeliveryOrThrow(id);

        DeliveryStatus newStatus;
        try {
            newStatus = DeliveryStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BusinessValidationException("Invalid delivery status: " + status);
        }

        validateTransition(delivery.getStatus(), newStatus);

        if (currentHubId != null) {
            delivery.setCurrentHub(hubRepository.findById(currentHubId)
                    .orElseThrow(() -> new ResourceNotFoundException("Hub not found for id: " + currentHubId)));
            if (delivery.getCurrentHub() != null && (location == null || location.equals("Admin Operations Center"))) {
                location = delivery.getCurrentHub().getName();
            }
        }

        delivery.setStatus(newStatus);
        Delivery updated = repo.save(delivery);
        log.info("Delivery {} status updated to {}", delivery.getTrackingNumber(), newStatus);
        
        String eventLoc = location != null ? location : (updated.getCurrentHub() != null ? updated.getCurrentHub().getName() : updated.getSource());
        publishStatusEvent(updated, description != null ? description : "Status updated to " + newStatus.name(), eventLoc);
        return deliveryMapper.toDto(updated);
    }

    @Override
    public DeliveryResponseDTO assignAgent(Long id, String agentName) {
        Delivery delivery = findDeliveryOrThrow(id);
        delivery.setAssignedAgent(agentName);
        Delivery updated = repo.save(delivery);
        log.info("Delivery {} assigned to agent {}", delivery.getTrackingNumber(), agentName);
        return deliveryMapper.toDto(updated);
    }

    @Override
    public void deleteDelivery(Long id) {
        Delivery delivery = findDeliveryOrThrow(id);
        repo.delete(delivery);
        log.info("Delivery {} deleted", delivery.getTrackingNumber());
    }

    @Override
    public List<DeliveryResponseDTO> getByStatus(String status) {
        DeliveryStatus ds = DeliveryStatus.valueOf(status.toUpperCase());
        return deliveryMapper.toDtoList(repo.findByStatus(ds));
    }

    @Override
    public List<DeliveryResponseDTO> getByAgent(String agent) {
        return deliveryMapper.toDtoList(repo.findByAssignedAgent(agent));
    }

    @Override
    public DeliveryResponseDTO getByTrackingNumber(String trackingNumber) {
        Delivery delivery = repo.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery not found with tracking number: " + trackingNumber));
        return deliveryMapper.toDto(delivery);
    }

    @Override
    public List<DeliveryResponseDTO> getUnassigned() {
        return deliveryMapper.toDtoList(repo.findByAssignedAgentIsNull());
    }

    @Override
    public List<DeliveryResponseDTO> getByMinPrice(Double minPrice) {
        return deliveryMapper.toDtoList(repo.findByPriceGreaterThanEqual(minPrice));
    }

    @Override
    public long countByCustomer(String username) {
        return repo.countByCustomerUsername(username);
    }

    @Override
    public Map<String, Long> getStatusSummary() {
        return Arrays.stream(DeliveryStatus.values())
                .collect(Collectors.toMap(
                        DeliveryStatus::name,
                        status -> repo.countByStatus(status)
                ));
    }

    // ==================== PRIVATE HELPERS ====================

    private Delivery findDeliveryOrThrow(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery not found for id: " + id));
    }

    private void validateSourceNotEqualToDestination(DeliveryRequestDTO dto) {
        if (dto.getSource().trim().equalsIgnoreCase(dto.getDestination().trim())) {
            throw new BusinessValidationException("Pickup and delivery addresses cannot be identical.");
        }
        
        if (dto.getSenderPhone().trim().equals(dto.getReceiverPhone().trim())) {
            throw new BusinessValidationException("Sender and receiver phone numbers cannot be the same.");
        }
        
        if (dto.getSenderName().trim().equalsIgnoreCase(dto.getReceiverName().trim())) {
            throw new BusinessValidationException("Sender and receiver names cannot be identical.");
        }
    }

    private void checkDuplicateBooking(DeliveryRequestDTO dto, String customerUsername) {
        LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);
        boolean exists = repo.existsByCustomerUsernameAndSenderNameAndReceiverNameAndSourceAndDestinationAndCreatedAtAfter(
                customerUsername, dto.getSenderName(), dto.getReceiverName(),
                dto.getSource(), dto.getDestination(), oneHourAgo
        );
        if (exists) {
            throw new BusinessValidationException("You recently created a similar booking. Please wait before submitting a duplicate request.");
        }
    }

    private void validatePriceSanity(DeliveryRequestDTO dto) {
        if (dto.getPrice() != null && dto.getPrice() > 100000) {
            throw new BusinessValidationException("Delivery price exceeds maximum allowed limit");
        }
    }

    private void publishStatusEvent(Delivery delivery, String description, String location) {
        DeliveryStatusEvent event = new DeliveryStatusEvent(
                delivery.getId(), delivery.getTrackingNumber(),
                delivery.getSenderPhone(), delivery.getStatus().name(),
                location != null ? location : delivery.getSource(), description, LocalDateTime.now()
        );
        eventPublisher.publishStatusUpdate(event);
    }

    private void validateTransition(DeliveryStatus currentStatus, DeliveryStatus newStatus) {
        if (currentStatus == null) {
            return;
        }
        if (currentStatus == newStatus) {
            throw new BusinessValidationException("Delivery is already in status: " + newStatus.name());
        }
        Set<DeliveryStatus> allowedNextStatuses = ALLOWED_TRANSITIONS.getOrDefault(currentStatus, Set.of());
        if (!allowedNextStatuses.contains(newStatus)) {
            throw new BusinessValidationException("Invalid status transition from "
                    + currentStatus.name() + " to " + newStatus.name());
        }
    }

    private static Map<DeliveryStatus, Set<DeliveryStatus>> buildAllowedTransitions() {
        Map<DeliveryStatus, Set<DeliveryStatus>> transitions = new EnumMap<>(DeliveryStatus.class);
        transitions.put(DeliveryStatus.PENDING_DISPATCH, EnumSet.of(DeliveryStatus.PICKED_UP, DeliveryStatus.CANCELLED, DeliveryStatus.DELIVERY_FAILED));
        transitions.put(DeliveryStatus.PICKED_UP, EnumSet.of(DeliveryStatus.ARRIVED_AT_HUB, DeliveryStatus.IN_TRANSIT, DeliveryStatus.DELIVERY_FAILED));
        transitions.put(DeliveryStatus.ARRIVED_AT_HUB, EnumSet.of(DeliveryStatus.DISPATCHED_FROM_HUB, DeliveryStatus.DELIVERY_FAILED));
        transitions.put(DeliveryStatus.DISPATCHED_FROM_HUB, EnumSet.of(DeliveryStatus.IN_TRANSIT, DeliveryStatus.DELIVERY_FAILED));
        transitions.put(DeliveryStatus.IN_TRANSIT, EnumSet.of(DeliveryStatus.REACHED_DESTINATION_HUB, DeliveryStatus.OUT_FOR_DELIVERY, DeliveryStatus.DELIVERY_FAILED));
        transitions.put(DeliveryStatus.REACHED_DESTINATION_HUB, EnumSet.of(DeliveryStatus.OUT_FOR_DELIVERY, DeliveryStatus.DELIVERY_FAILED));
        transitions.put(DeliveryStatus.OUT_FOR_DELIVERY, EnumSet.of(DeliveryStatus.DELIVERED, DeliveryStatus.DELIVERY_FAILED));

        transitions.put(DeliveryStatus.BOOKED, EnumSet.of(DeliveryStatus.PENDING_DISPATCH, DeliveryStatus.PICKED_UP, DeliveryStatus.CANCELLED, DeliveryStatus.DELIVERY_FAILED));
        transitions.put(DeliveryStatus.CREATED, EnumSet.of(DeliveryStatus.PENDING_DISPATCH, DeliveryStatus.BOOKED, DeliveryStatus.CANCELLED));
        transitions.put(DeliveryStatus.SHIPPED, EnumSet.of(DeliveryStatus.IN_TRANSIT, DeliveryStatus.DELIVERED, DeliveryStatus.DELIVERY_FAILED));
        transitions.put(DeliveryStatus.DELAYED, EnumSet.of(DeliveryStatus.IN_TRANSIT, DeliveryStatus.DELIVERED, DeliveryStatus.DELIVERY_FAILED));
        transitions.put(DeliveryStatus.FAILED, EnumSet.of(DeliveryStatus.RETURNED));
        return transitions;
    }
}
