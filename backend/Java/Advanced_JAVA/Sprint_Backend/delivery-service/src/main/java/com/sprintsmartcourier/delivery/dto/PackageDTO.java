package com.sprintsmartcourier.delivery.dto;

import lombok.Data;

@Data
public class PackageDTO {
    private Double weight;
    private Double length;
    private Double width;
    private Double height;
    private String type;
    private String description;
}