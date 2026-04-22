package com.smartcourier.delivery.service.impl;

import com.smartcourier.delivery.entity.Delivery;
import com.smartcourier.delivery.enums.DeliveryStatus;
import com.smartcourier.delivery.exception.*;
import com.smartcourier.delivery.repository.DeliveryRepository;
import com.smartcourier.delivery.service.DeliveryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository repo;

    public DeliveryServiceImpl(DeliveryRepository repo) {
        this.repo = repo;
    }

    /**
     * Create delivery with:
     * ✔ customer identity
     * ✔ default status
     * ✔ auto-generated tracking number
     */
    @Override
    public Delivery createDelivery(Delivery d, String username) {

        d.setCustomerUsername(username);

        // 🔥 Generate unique tracking number
        d.setTrackingNumber(UUID.randomUUID().toString());

        d.setStatus(DeliveryStatus.CREATED);

        return repo.save(d);
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        return repo.findAll();
    }

    @Override
    public Delivery getDeliveryById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new DeliveryNotFoundException("Delivery not found"));
    }

    @Override
    public Delivery updateDelivery(Long id, Delivery d) {
        Delivery existing = getDeliveryById(id);

        existing.setSenderName(d.getSenderName());
        existing.setReceiverName(d.getReceiverName());
        existing.setSource(d.getSource());
        existing.setDestination(d.getDestination());
        existing.setPrice(d.getPrice());

        return repo.save(existing);
    }

    @Override
    public void deleteDelivery(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Delivery assignAgent(Long id, String agent) {
        Delivery d = getDeliveryById(id);
        d.setAssignedAgent(agent);
        return repo.save(d);
    }

    /**
     * Update status using ENUM (safe transitions)
     */
    @Override
    public Delivery updateStatus(Long id, String status) {

        Delivery d = getDeliveryById(id);

        try {
            DeliveryStatus newStatus = DeliveryStatus.valueOf(status.toUpperCase());
            d.setStatus(newStatus);
        } catch (IllegalArgumentException e) {
            throw new InvalidStatusException("Invalid status value");
        }

        return repo.save(d);
    }

    @Override
    public List<Delivery> getByStatus(String status) {
        try {
            DeliveryStatus s = DeliveryStatus.valueOf(status.toUpperCase());
            return repo.findByStatus(s);
        } catch (Exception e) {
            throw new InvalidStatusException("Invalid status");
        }
    }

    @Override
    public List<Delivery> getByAgent(String agent) {
        return repo.findByAssignedAgent(agent);
    }

    @Override
    public List<Delivery> getByCustomer(String username) {
        return repo.findByCustomerUsername(username);
    }
}