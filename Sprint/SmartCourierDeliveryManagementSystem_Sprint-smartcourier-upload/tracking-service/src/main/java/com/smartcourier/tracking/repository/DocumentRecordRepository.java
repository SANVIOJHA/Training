package com.smartcourier.tracking.repository;

import com.smartcourier.tracking.entity.DocumentRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRecordRepository extends JpaRepository<DocumentRecord, Long> {

    List<DocumentRecord> findByTrackingNumberOrderByUploadedAtDesc(String trackingNumber);
}
