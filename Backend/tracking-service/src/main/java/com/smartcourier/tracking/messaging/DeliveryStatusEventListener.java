package com.smartcourier.tracking.messaging;

import com.smartcourier.common.constant.SmartCourierConstants;
import com.smartcourier.common.event.DeliveryStatusEvent;
import com.smartcourier.common.event.UserRegisteredEvent;
import com.smartcourier.tracking.entity.TrackingEvent;
import com.smartcourier.tracking.service.TrackingService;
import com.smartcourier.tracking.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Consumes events from RabbitMQ using shared event DTOs from common module.
 */
@Component
public class DeliveryStatusEventListener {

    private static final Logger log = LoggerFactory.getLogger(DeliveryStatusEventListener.class);

    private final TrackingService trackingService;
    private final SmsService smsService;

    public DeliveryStatusEventListener(TrackingService trackingService, SmsService smsService) {
        this.trackingService = trackingService;
        this.smsService = smsService;
    }

    @RabbitListener(queues = SmartCourierConstants.DELIVERY_STATUS_QUEUE)
    public void consumeDeliveryEvent(DeliveryStatusEvent event) {
        log.info("Received delivery status event: deliveryId={}, status={}", event.getDeliveryId(), event.getStatus());

        TrackingEvent trackingEvent = new TrackingEvent();
        trackingEvent.setDeliveryId(event.getDeliveryId());
        trackingEvent.setStatus(event.getStatus());
        trackingEvent.setLocation(event.getLocation() != null ? event.getLocation() : SmartCourierConstants.DEFAULT_LOCATION);
        trackingEvent.setDescription(event.getDescription() != null ? event.getDescription() : "Event received from delivery-service");
        trackingEvent.setTimestamp(event.getEventTime());
        trackingService.addEvent(trackingEvent);

        if (event.getPhoneNumber() != null && !event.getPhoneNumber().isBlank()) {
            String smsMessage = String.format("SmartCourier: Delivery %s is now %s. Location: %s.",
                    event.getTrackingNumber(), event.getStatus(), trackingEvent.getLocation());
            smsService.sendSms(event.getPhoneNumber(), smsMessage);
        }
    }

    @RabbitListener(queues = SmartCourierConstants.USER_REGISTERED_QUEUE)
    public void consumeUserRegisteredEvent(UserRegisteredEvent event) {
        log.info("Received user registered event: username={}", event.getUsername());

        if (event.getPhoneNumber() != null && !event.getPhoneNumber().isBlank()) {
            String smsMessage = String.format("Welcome to SmartCourier, %s! Your account has been created successfully.", event.getUsername());
            smsService.sendSms(event.getPhoneNumber(), smsMessage);
        }
    }
}
