package com.sprintsmartcourier.delivery.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private String name;
    private String phone;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}