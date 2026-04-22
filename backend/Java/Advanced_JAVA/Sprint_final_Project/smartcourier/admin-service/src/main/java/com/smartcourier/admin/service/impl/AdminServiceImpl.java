package com.smartcourier.admin.service.impl;

import com.smartcourier.admin.dto.AdminDashboardDTO;
import com.smartcourier.admin.entity.Report;
import com.smartcourier.admin.exception.ResourceNotFoundException;
import com.smartcourier.admin.repository.ReportRepository;
import com.smartcourier.admin.service.AdminService;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    // Dependencies
    private final WebClient.Builder webClientBuilder;
    private final ReportRepository repo;

    // SINGLE constructor injection
    public AdminServiceImpl(WebClient.Builder webClientBuilder,
                            ReportRepository repo) {
        this.webClientBuilder = webClientBuilder;
        this.repo = repo;
    }

    // ================= DASHBOARD =================
    @Override
    public AdminDashboardDTO getDashboard() {

        // Call Delivery Service via Eureka (LOAD BALANCED)
        List<Map> deliveries = webClientBuilder.build()
                .get()
                .uri("http://DELIVERY-SERVICE/deliveries")
                .retrieve()
                .bodyToFlux(Map.class)
                .collectList()
                .block();

        int total = deliveries.size();

        int shipped = (int) deliveries.stream()
                .filter(d -> "SHIPPED".equals(d.get("status")))
                .count();

        int delivered = (int) deliveries.stream()
                .filter(d -> "DELIVERED".equals(d.get("status")))
                .count();

        int cancelled = (int) deliveries.stream()
                .filter(d -> "CANCELLED".equals(d.get("status")))
                .count();

        AdminDashboardDTO dto = new AdminDashboardDTO();
        dto.setTotalDeliveries(total);
        dto.setShipped(shipped);
        dto.setDelivered(delivered);
        dto.setCancelled(cancelled);

        return dto;
    }

    // ================= REPORT =================
    @Override
    public Report generateReport(Report report) {

        report.setReportDate(LocalDate.now());

        // Dummy analytics (can replace later with real data)
        report.setReportData("Total Deliveries: 100 | Completed: 80 | Pending: 20");

        return repo.save(report);
    }

    @Override
    public List<Report> getAllReports() {
        return repo.findAll();
    }

    @Override
    public Report getReport(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found"));
    }

    @Override
    public void deleteReport(Long id) {
        repo.deleteById(id);
    }
}