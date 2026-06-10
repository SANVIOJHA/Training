package com.smartcourier.delivery.messaging;

import com.smartcourier.common.constant.SmartCourierConstants;
import com.smartcourier.common.event.DeliveryStatusEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * Publishes delivery status events to RabbitMQ.
 * Uses shared constants and event DTOs from common module.
 */
@Component
public class DeliveryEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(DeliveryEventPublisher.class);

    public static final String EXCHANGE = SmartCourierConstants.DELIVERY_EVENTS_EXCHANGE;
    public static final String ROUTING_KEY = SmartCourierConstants.DELIVERY_STATUS_ROUTING_KEY;

    private final RabbitTemplate rabbitTemplate;

    public DeliveryEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishStatusUpdate(DeliveryStatusEvent event) {
        try {
            rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, event);
            log.info("Published delivery status event: deliveryId={}, status={}", event.getDeliveryId(), event.getStatus());
        } catch (Exception e) {
            log.error("Failed to publish delivery status event: {}", e.getMessage());
        }
    }
}
