package com.smartcourier.delivery.entity;

import com.smartcourier.delivery.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Unique tracking number (UUID)
     */
    @Column(unique = true, nullable = false)
    private String trackingNumber;

    /**
     * Customer identity (injected from Gateway)
     */
    @Column(nullable = false)
    private String customerUsername;

    private String senderName;
    private String receiverName;
    private String senderAddress;
    private String receiverAddress;

    private String source;
    private String destination;
    private String serviceType;
    private String packageType;
    private Double packageWeight;
    private LocalDate pickupDate;
    private String packageDescription;
    private String notes;
    private String currentHub;

    /**
     * ENUM instead of String → safer + validated
     */
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private String assignedAgent;
    private Boolean exceptionResolved;

    @Column(length = 2000)
    private String exceptionNotes;

    private Double price;
}
