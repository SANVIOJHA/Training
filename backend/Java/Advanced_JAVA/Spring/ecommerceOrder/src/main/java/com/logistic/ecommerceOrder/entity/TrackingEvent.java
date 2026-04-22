package com.logistic.ecommerceOrder.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrackingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EventType eventType; // ARRIVED / DEPARTED / DELIVERED

    private LocalDateTime timestamp;

    private String location;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;
}