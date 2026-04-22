package com.smartcourier.delivery.service;

import com.smartcourier.delivery.entity.Delivery;

import java.util.List;

public interface DeliveryService {

    Delivery createDelivery(Delivery d, String username);

    List<Delivery> getAllDeliveries();

    Delivery getDeliveryById(Long id);

    Delivery updateDelivery(Long id, Delivery d);

    void deleteDelivery(Long id);

    Delivery assignAgent(Long id, String agent);

    Delivery updateStatus(Long id, String status);

    List<Delivery> getByStatus(String status);

    List<Delivery> getByAgent(String agent);

    //
    List<Delivery> getByCustomer(String username);
}