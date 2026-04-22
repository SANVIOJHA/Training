package com.logistic.ecommerceOrder.service.impl;

import com.logistic.ecommerceOrder.dto.ShipmentRequestDto;
import com.logistic.ecommerceOrder.entity.*;
import com.logistic.ecommerceOrder.exception.ResourceNotFoundException;
import com.logistic.ecommerceOrder.repository.*;
import com.logistic.ecommerceOrder.service.ShipmentService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepo;
    private final WarehouseRepository warehouseRepo;

    @Override
    @Transactional
    public Shipment createShipment(ShipmentRequestDto dto) {

        Warehouse warehouse = warehouseRepo.findById(dto.getWarehouseId())
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));

        Shipment shipment = new Shipment();
        shipment.setTrackingNumber(dto.getTrackingNumber());
        shipment.setStatus(dto.getStatus());
        shipment.setCustomerEmail(dto.getCustomerEmail());
        shipment.setWarehouse(warehouse);

        return shipmentRepo.save(shipment);
    }

    @Override
    public List<Shipment> getAllShipments() {
        return shipmentRepo.findAll();
    }

    @Override
    public Shipment getShipmentById(Long id) {
        return shipmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found"));
    }

    @Override
    @Transactional
    public Shipment updateShipment(Long id, ShipmentRequestDto dto) {

        Shipment shipment = getShipmentById(id);

        shipment.setTrackingNumber(dto.getTrackingNumber());
        shipment.setStatus(dto.getStatus());
        shipment.setCustomerEmail(dto.getCustomerEmail());

        return shipmentRepo.save(shipment);
    }

    @Override
    public void deleteShipment(Long id) {
        shipmentRepo.delete(getShipmentById(id));
    }
}