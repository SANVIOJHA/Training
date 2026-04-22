package com.lovely.rabbit.dto;

import lombok.Data;

@Data
public class Order {
    private String id;
    private String name;
    private int quantity;
}