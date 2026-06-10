package com.smartcourier.delivery.entity;

import com.smartcourier.delivery.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "delivery")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Unique tracking number (SC-UUID format)
     */
    @Column(unique = true, nullable = false, length = 100)
    private String trackingNumber;

    /**
     * Customer identity (injected from Gateway)
     */
    @Column(nullable = false, length = 100)
    private String customerUsername;

    @Column(length = 255)
    private String senderName;

    @Column(name = "customer_mobile_number", length = 20)
    private String senderPhone;

    @Column(length = 255)
    private String receiverName;

    @Column(length = 20)
    private String receiverPhone;

    @Column(length = 500)
    private String source;

    @Column(length = 500)
    private String destination;

    /**
     * ENUM instead of String — safer + validated
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private DeliveryStatus status;

    @Column(length = 100)
    private String assignedAgent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "current_hub_id")
    private Hub currentHub;

    private Double price;

    private Double weight;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}