package com.smartcourier.delivery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartcourier.delivery.dto.AgentAssignDTO;
import com.smartcourier.delivery.dto.DeliveryRequestDTO;
import com.smartcourier.delivery.dto.DeliveryResponseDTO;
import com.smartcourier.delivery.dto.StatusUpdateDTO;
import com.smartcourier.delivery.enums.DeliveryStatus;
import com.smartcourier.delivery.exception.GlobalExceptionHandler;
import com.smartcourier.delivery.service.DeliveryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DeliveryControllerTest {

    @Mock
    private DeliveryService deliveryService;

    @InjectMocks
    private DeliveryController deliveryController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(deliveryController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void getMyDeliveriesShouldReturnCustomerDeliveries() throws Exception {
        when(deliveryService.getDeliveriesByCustomer("alice")).thenReturn(List.of(dto(1L, "TRK1", "BOOKED")));

        mockMvc.perform(get("/deliveries/my").header("X-User", "alice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].trackingNumber").value("TRK1"));
    }

    @Test
    void getMyDeliveryCountShouldReturnCount() throws Exception {
        when(deliveryService.countByCustomer("alice")).thenReturn(4L);

        mockMvc.perform(get("/deliveries/my/count").header("X-User", "alice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(4));
    }

    @Test
    void createShouldReturnCreatedDelivery() throws Exception {
        when(deliveryService.createDelivery(any(DeliveryRequestDTO.class), eq("alice")))
                .thenReturn(dto(1L, "TRK1", "BOOKED"));

        mockMvc.perform(post("/deliveries")
                        .header("X-User", "alice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.status").value("BOOKED"));
    }

    @Test
    void getAllShouldReturnAllDeliveries() throws Exception {
        when(deliveryService.getAllDeliveries()).thenReturn(List.of(dto(1L, "TRK1", "BOOKED")));

        mockMvc.perform(get("/deliveries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(1));
    }

    @Test
    void getByIdShouldReturnDelivery() throws Exception {
        when(deliveryService.getDeliveryById(1L)).thenReturn(dto(1L, "TRK1", "BOOKED"));

        mockMvc.perform(get("/deliveries/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.trackingNumber").value("TRK1"));
    }

    @Test
    void getByTrackingNumberShouldReturnDelivery() throws Exception {
        when(deliveryService.getByTrackingNumber("TRK1")).thenReturn(dto(1L, "TRK1", "BOOKED"));

        mockMvc.perform(get("/deliveries/tracking/TRK1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.trackingNumber").value("TRK1"));
    }

    @Test
    void deleteShouldReturnSuccessMessage() throws Exception {
        mockMvc.perform(delete("/deliveries/1"))
                .andExpect(status().isOk());
    }

    @Test
    void assignAgentWithBodyShouldReturnAssignedDelivery() throws Exception {
        AgentAssignDTO request = new AgentAssignDTO();
        request.setAgent("agent2");
        DeliveryResponseDTO response = dto(1L, "TRK1", "BOOKED");
        response.setAssignedAgent("agent2");
        when(deliveryService.assignAgent(1L, "agent2")).thenReturn(response);

        mockMvc.perform(put("/deliveries/1/assign")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.assignedAgent").value("agent2"));
    }

    @Test
    void updateStatusShouldReturnUpdatedStatus() throws Exception {
        StatusUpdateDTO request = new StatusUpdateDTO();
        request.setStatus("IN_TRANSIT");
        request.setLocation("Hub");
        request.setDescription("Moved");
        when(deliveryService.updateStatus(1L, "IN_TRANSIT", "Hub", "Moved", null))
                .thenReturn(dto(1L, "TRK1", "IN_TRANSIT"));

        mockMvc.perform(put("/deliveries/1/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.status").value("IN_TRANSIT"));
    }

    @Test
    void getByStatusShouldReturnFilteredDeliveries() throws Exception {
        when(deliveryService.getByStatus("IN_TRANSIT")).thenReturn(List.of(dto(1L, "TRK1", "IN_TRANSIT")));

        mockMvc.perform(get("/deliveries/status/IN_TRANSIT"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].status").value("IN_TRANSIT"));
    }

    @Test
    void getUnassignedShouldReturnUnassignedDeliveries() throws Exception {
        when(deliveryService.getUnassigned()).thenReturn(List.of(dto(1L, "TRK1", "BOOKED")));

        mockMvc.perform(get("/deliveries/unassigned"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(1));
    }

    @Test
    void statusSummaryShouldReturnMap() throws Exception {
        when(deliveryService.getStatusSummary()).thenReturn(Map.of("BOOKED", 2L));

        mockMvc.perform(get("/deliveries/summary/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.BOOKED").value(2));
    }


    private DeliveryRequestDTO request() {
        DeliveryRequestDTO request = new DeliveryRequestDTO();
        request.setSenderName("Alice");
        request.setSenderPhone("1234567890");
        request.setReceiverName("Bob");
        request.setReceiverPhone("0987654321");
        request.setSource("Chennai");
        request.setDestination("Bengaluru");
        request.setPrice(499.0);
        request.setWeight(2.0);
        return request;
    }


    private DeliveryResponseDTO dto(Long id, String trackingNumber, String status) {
        DeliveryResponseDTO dto = new DeliveryResponseDTO();
        dto.setId(id);
        dto.setTrackingNumber(trackingNumber);
        dto.setSenderName("Alice");
        dto.setReceiverName("Bob");
        dto.setSource("Chennai");
        dto.setDestination("Bengaluru");
        dto.setStatus(status);
        dto.setPrice(499.0);
        return dto;
    }
}

