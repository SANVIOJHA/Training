package com.smartcourier.admin.client;

import com.smartcourier.admin.dto.DeliverySummaryDTO;
import com.smartcourier.admin.dto.DeliveryResolutionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "delivery-service")
public interface DeliveryClient {

    @GetMapping("/deliveries")
    List<DeliverySummaryDTO> getAllDeliveries();

    @PutMapping("/deliveries/{id}/resolve")
    DeliverySummaryDTO resolveDelivery(@PathVariable Long id, @RequestBody DeliveryResolutionRequest request);
}
