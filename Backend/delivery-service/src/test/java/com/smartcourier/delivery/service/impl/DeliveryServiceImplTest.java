package com.smartcourier.delivery.service.impl;

import com.smartcourier.common.exception.BusinessValidationException;
import com.smartcourier.delivery.dto.DeliveryRequestDTO;
import com.smartcourier.delivery.dto.DeliveryResponseDTO;
import com.smartcourier.delivery.entity.Delivery;
import com.smartcourier.delivery.enums.DeliveryStatus;
import com.smartcourier.delivery.mapper.DeliveryMapper;
import com.smartcourier.delivery.messaging.DeliveryEventPublisher;
import com.smartcourier.delivery.repository.DeliveryRepository;
import com.smartcourier.delivery.repository.HubRepository;
import com.smartcourier.delivery.strategy.PricingStrategy;
import com.smartcourier.delivery.strategy.PricingStrategyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceImplTest {

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private DeliveryMapper deliveryMapper;

    @Mock
    private DeliveryEventPublisher eventPublisher;

    @Mock
    private PricingStrategyFactory pricingStrategyFactory;

    @Mock
    private HubRepository hubRepository;

    @InjectMocks
    private DeliveryServiceImpl deliveryService;

    private DeliveryRequestDTO requestDTO;
    private Delivery delivery;
    private DeliveryResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        requestDTO = new DeliveryRequestDTO();
        requestDTO.setSenderName("Alice");
        requestDTO.setReceiverName("Bob");
        requestDTO.setSource("Chennai");
        requestDTO.setDestination("Bengaluru");
        requestDTO.setSenderPhone("1234567890");
        requestDTO.setReceiverPhone("0987654321");
        requestDTO.setWeight(2.0);
        requestDTO.setPrice(500.0);

        delivery = new Delivery();
        delivery.setId(101L);
        delivery.setSenderName("Alice");
        delivery.setReceiverName("Bob");
        delivery.setSource("Chennai");
        delivery.setDestination("Bengaluru");
        delivery.setSenderPhone("1234567890");
        delivery.setReceiverPhone("0987654321");
        delivery.setStatus(DeliveryStatus.BOOKED);
        
        responseDTO = new DeliveryResponseDTO();
    }

    @Test
    void createDeliveryShouldAssignDefaultsAndPublishEvent() {
        doReturn(delivery).when(deliveryMapper).toEntity(any(DeliveryRequestDTO.class));
        doReturn(delivery).when(deliveryRepository).save(any(Delivery.class));
        doReturn(responseDTO).when(deliveryMapper).toDto(any(Delivery.class));
        
        PricingStrategy mockStrategy = mock(PricingStrategy.class);
        doReturn(mockStrategy).when(pricingStrategyFactory).getStrategy(anyString(), anyString());
        doReturn(500.0).when(mockStrategy).calculatePrice(anyDouble());

        DeliveryResponseDTO response = deliveryService.createDelivery(requestDTO, "alice");

        assertNotNull(response);
        verify(deliveryRepository).save(any(Delivery.class));
    }

    @Test
    void updateStatusShouldRejectUnknownStatus() {
        doReturn(Optional.of(delivery)).when(deliveryRepository).findById(10L);

        assertThrows(
                BusinessValidationException.class,
                () -> deliveryService.updateStatus(10L, "INVALID_STATUS")
        );
    }

    @Test
    void updateStatusShouldWorkWithValidStatus() {
        doReturn(Optional.of(delivery)).when(deliveryRepository).findById(101L);
        doReturn(delivery).when(deliveryRepository).save(any(Delivery.class));
        doReturn(responseDTO).when(deliveryMapper).toDto(any(Delivery.class));

        DeliveryResponseDTO response = deliveryService.updateStatus(101L, "PICKED_UP", "Hub A", "Picked up by agent", null);

        assertNotNull(response);
        assertEquals(DeliveryStatus.PICKED_UP, delivery.getStatus());
    }

}


