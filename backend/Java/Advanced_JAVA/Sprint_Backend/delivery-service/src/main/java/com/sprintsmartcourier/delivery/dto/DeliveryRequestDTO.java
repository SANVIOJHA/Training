package com.sprintsmartcourier.delivery.dto;

import lombok.Data;

@Data
public class DeliveryRequestDTO {

    private AddressDTO senderAddress;
    private AddressDTO receiverAddress;
    private PackageDTO packageDetails;

    private Double cost;
}