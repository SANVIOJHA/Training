package com.sprintsmartcourier.delivery.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "deliveries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trackingNumber;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private Double cost;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_address_id")
    private Address senderAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "receiver_address_id")
    private Address receiverAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "package_id")
    private Package packageDetails;
}
/**
 * why relationships are being used here can't we simply do
 class Delivery {
      String senderName;
      String senderStreet;
      String senderCity;
      String receiverName;
      String receiverStreet;
      ...
  }


 this will lead to
 Problems:
 Data duplication
 Hard to maintain
 No scalability
 Violates database normalization

 so the solution for this is that we use relationship mapping as it will be



 Delivery → Address (sender)
 Delivery → Address (receiver)
 Delivery → Package

 This maps to:

 Delivery Table
 |
 |--- sender_address_id → Address Table
 |--- receiver_address_id → Address Table
 |--- package_id → Package Table



 *
 */