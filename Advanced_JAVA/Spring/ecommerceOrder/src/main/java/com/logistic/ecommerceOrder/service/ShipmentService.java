package com.logistic.ecommerceOrder.service;

import com.logistic.ecommerceOrder.dto.ShipmentRequestDto;
import com.logistic.ecommerceOrder.entity.Shipment;

import java.util.List;

public interface ShipmentService {

    Shipment createShipment(ShipmentRequestDto dto);

    List<Shipment> getAllShipments();

    Shipment getShipmentById(Long id);

    Shipment updateShipment(Long id, ShipmentRequestDto dto);

    void deleteShipment(Long id);
}