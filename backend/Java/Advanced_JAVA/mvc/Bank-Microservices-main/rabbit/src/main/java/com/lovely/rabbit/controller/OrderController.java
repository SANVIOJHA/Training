package com.lovely.rabbit.controller;

import com.lovely.rabbit.dto.Order;
import com.lovely.rabbit.msg.producer.RabbitMqPojoProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final RabbitMqPojoProducer orderProducer;

    @PostMapping
    public String sendOrder(@RequestBody Order order) {
        orderProducer.sendOrder(order);
        return "Order sent successfully!";
    }
}