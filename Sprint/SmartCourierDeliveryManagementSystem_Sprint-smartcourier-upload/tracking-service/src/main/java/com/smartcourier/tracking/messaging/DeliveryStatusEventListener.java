package com.smartcourier.tracking.messaging;

import com.smartcourier.tracking.config.RabbitMqConfig;
import com.smartcourier.tracking.entity.DeliveryProofRecord;
import com.smartcourier.tracking.entity.TrackingEvent;
import com.smartcourier.tracking.repository.DeliveryProofRepository;
import com.smartcourier.tracking.service.TrackingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DeliveryStatusEventListener {

    private final TrackingService trackingService;
    private final DeliveryProofRepository deliveryProofRepository;

    public DeliveryStatusEventListener(TrackingService trackingService, DeliveryProofRepository deliveryProofRepository) {
        this.trackingService = trackingService;
        this.deliveryProofRepository = deliveryProofRepository;
    }

    @RabbitListener(queues = RabbitMqConfig.TRACKING_QUEUE)
    public void consume(DeliveryStatusEvent event) {
        TrackingEvent trackingEvent = new TrackingEvent();
        trackingEvent.setDeliveryId(event.getDeliveryId());
        trackingEvent.setTrackingNumber(event.getTrackingNumber());
        trackingEvent.setStatus(event.getStatus());
        trackingEvent.setLocation(event.getLocation() != null ? event.getLocation() : "Transit hub");
        trackingEvent.setDescription(event.getDescription() != null ? event.getDescription() : "Event received from delivery-service");
        trackingEvent.setTimestamp(event.getEventTime());
        trackingService.addEvent(trackingEvent);

        if ("DELIVERED".equalsIgnoreCase(event.getStatus()) && !deliveryProofRepository.existsByDeliveryId(event.getDeliveryId())) {
            DeliveryProofRecord proof = new DeliveryProofRecord();
            proof.setDeliveryId(event.getDeliveryId());
            proof.setTrackingNumber(event.getTrackingNumber());
            proof.setRecipientName("Verified Recipient");
            proof.setDeliveredLocation(trackingEvent.getLocation());
            proof.setConfirmationMessage(event.getDescription() != null ? event.getDescription() : "Delivery confirmed");
            proof.setDeliveredAt(event.getEventTime() != null ? event.getEventTime() : LocalDateTime.now());
            trackingService.saveProof(proof);
        }
    }
}
