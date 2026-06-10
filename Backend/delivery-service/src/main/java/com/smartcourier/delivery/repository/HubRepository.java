package com.smartcourier.delivery.repository;

import com.smartcourier.delivery.entity.Hub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HubRepository extends JpaRepository<Hub, Long> {
    Optional<Hub> findByCode(String code);
}
