package com.smartcourier.admin.client;

import com.smartcourier.admin.dto.DeliverySummaryDTO;
import com.smartcourier.common.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "delivery-service")
public interface DeliveryClient {

    @GetMapping("/deliveries")
    ApiResponse<List<DeliverySummaryDTO>> getAllDeliveries();

    @org.springframework.web.bind.annotation.PutMapping("/deliveries/{id}/status")
    ApiResponse<DeliverySummaryDTO> updateStatus(@org.springframework.web.bind.annotation.PathVariable("id") Long id, @org.springframework.web.bind.annotation.RequestBody com.smartcourier.admin.dto.StatusUpdateDTO dto);
}
