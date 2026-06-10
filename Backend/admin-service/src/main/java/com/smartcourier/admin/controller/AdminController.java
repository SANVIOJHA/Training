package com.smartcourier.admin.controller;

import com.smartcourier.admin.dto.AdminDashboardDTO;
import com.smartcourier.admin.dto.DeliverySummaryDTO;
import com.smartcourier.admin.dto.ReportDTO;
import com.smartcourier.admin.dto.StatusUpdateDTO;
import com.smartcourier.admin.entity.Report;
import com.smartcourier.admin.service.AdminService;
import com.smartcourier.common.dto.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService service;
    private final com.smartcourier.admin.client.DeliveryClient deliveryClient;

    public AdminController(AdminService service, com.smartcourier.admin.client.DeliveryClient deliveryClient) {
        this.service = service;
        this.deliveryClient = deliveryClient;
    }

    @PostMapping("/reports")
    public ResponseEntity<ApiResponse<Report>> create(@Valid @RequestBody ReportDTO dto) {
        Report report = new Report();
        report.setReportType(dto.getReportType());
        report.setFromDate(dto.getFromDate());
        report.setToDate(dto.getToDate());
        report.setGeneratedBy(dto.getGeneratedBy());
        return ResponseEntity.ok(ApiResponse.success("Report created", service.generateReport(report)));
    }

    @GetMapping("/reports")
    public ResponseEntity<ApiResponse<List<Report>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success("Reports retrieved", service.getAllReports()));
    }

    @GetMapping("/reports/{id}")
    public ResponseEntity<ApiResponse<Report>> get(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Report retrieved", service.getReport(id)));
    }

    @PutMapping("/reports/{id}")
    public ResponseEntity<ApiResponse<Report>> update(@PathVariable Long id, @Valid @RequestBody ReportDTO dto) {
        return ResponseEntity.ok(ApiResponse.success("Report updated", service.updateReport(id, dto)));
    }

    @DeleteMapping("/reports/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        service.deleteReport(id);
        return ResponseEntity.ok(ApiResponse.success("Report deleted"));
    }

    @GetMapping("/reports/count")
    public ResponseEntity<ApiResponse<Long>> count() {
        return ResponseEntity.ok(ApiResponse.success("Report count retrieved", service.countReports()));
    }

    @GetMapping("/reports/latest")
    public ResponseEntity<ApiResponse<Report>> latest() {
        return ResponseEntity.ok(ApiResponse.success("Latest report retrieved", service.getLatestReport()));
    }

    @GetMapping("/reports/type/{type}")
    public ResponseEntity<ApiResponse<List<Report>>> byType(@PathVariable String type) {
        return ResponseEntity.ok(ApiResponse.success("Reports retrieved", service.getReportsByType(type)));
    }

    @GetMapping("/reports/exist/{id}")
    public ResponseEntity<ApiResponse<Boolean>> exists(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Existence check", service.reportExists(id)));
    }

    @GetMapping("/reports/by-date/{date}")
    public ResponseEntity<ApiResponse<List<Report>>> byDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(ApiResponse.success("Reports retrieved", service.getReportsByDate(date)));
    }

    @GetMapping("/reports/by-range")
    public ResponseEntity<ApiResponse<List<Report>>> byRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return ResponseEntity.ok(ApiResponse.success("Reports retrieved", service.getReportsByRange(from, to)));
    }

    @GetMapping("/reports/by-user/{username}")
    public ResponseEntity<ApiResponse<List<Report>>> byUser(@PathVariable String username) {
        return ResponseEntity.ok(ApiResponse.success("Reports retrieved", service.getReportsByGeneratedBy(username)));
    }

    @GetMapping("/reports/summary/type")
    public ResponseEntity<ApiResponse<Map<String, Long>>> reportTypeSummary() {
        return ResponseEntity.ok(ApiResponse.success("Summary retrieved", service.reportTypeSummary()));
    }

    @GetMapping("/dashboard")
    public ResponseEntity<ApiResponse<AdminDashboardDTO>> dashboard() {
        return ResponseEntity.ok(ApiResponse.success("Dashboard data retrieved", service.getDashboard()));
    }

    @GetMapping("/deliveries")
    public ResponseEntity<ApiResponse<List<DeliverySummaryDTO>>> getAllDeliveries() {
        return ResponseEntity.ok(ApiResponse.success("Deliveries retrieved", deliveryClient.getAllDeliveries().getData()));
    }

    @PutMapping("/deliveries/{id}/status")
    public ResponseEntity<ApiResponse<DeliverySummaryDTO>> updateDeliveryStatus(
            @PathVariable Long id,
            @RequestBody StatusUpdateDTO dto) {
        return ResponseEntity.ok(ApiResponse.success("Delivery status updated", deliveryClient.updateStatus(id, dto).getData()));
    }

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> health() {
        return ResponseEntity.ok(ApiResponse.success("Admin Service Running"));
    }
}
