package com.smartcourier.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Event published when a delivery's status changes.
 * Published by delivery-service, consumed by tracking-service.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryStatusEvent implements Serializable {

    private Long deliveryId;
    private String trackingNumber;
    private String phoneNumber;
    private String status;
    private String location;
    private String description;
    private LocalDateTime eventTime;
}
