package com.smartcourier.tracking.repository;

import com.smartcourier.tracking.entity.DeliveryProofRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryProofRepository extends JpaRepository<DeliveryProofRecord, Long> {

    Optional<DeliveryProofRecord> findByDeliveryId(Long deliveryId);

    boolean existsByDeliveryId(Long deliveryId);
}
