package com.logistic.ecommerceOrder.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentResponseDto {

    private Long id;
    private String trackingNumber;
    private String status;
    private String customerEmail;
    private String warehouseName;
}