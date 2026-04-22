package com.smartcourier.admin.controller;

import com.smartcourier.admin.dto.ReportDTO;
import com.smartcourier.admin.entity.Report;
import com.smartcourier.admin.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    // 1. Generate report
    @PostMapping("/reports")
    public Report create(@RequestBody Report report) {
        return service.generateReport(report);
    }

    // 2. Get all reports
    @GetMapping("/reports")
    public List<Report> getAll() {
        return service.getAllReports();
    }

    // 3. Get by ID
    @GetMapping("/reports/{id}")
    public Report get(@PathVariable Long id) {
        return service.getReport(id);
    }

    // 4. Delete report
    @DeleteMapping("/reports/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteReport(id);
        return "Deleted";
    }

    // 5. Health check
    @GetMapping("/health")
    public String health() {
        return "Admin Service Running";
    }

    // 6–15 (extra endpoints)
    @GetMapping("/reports/count")
    public int count() {
        return service.getAllReports().size();
    }

    @GetMapping("/reports/latest")
    public Report latest() {
        List<Report> list = service.getAllReports();
        return list.get(list.size() - 1);
    }

    @GetMapping("/reports/type/{type}")
    public List<Report> byType(@PathVariable String type) {
        return service.getAllReports()
                .stream()
                .filter(r -> r.getReportType().equalsIgnoreCase(type))
                .toList();
    }

    @PutMapping("/reports/{id}")
    public Report update(@PathVariable Long id, @RequestBody Report updated) {
        Report r = service.getReport(id);
        r.setReportType(updated.getReportType());
        return service.generateReport(r);
    }

    @GetMapping("/reports/exist/{id}")
    public boolean exists(@PathVariable Long id) {
        try {
            service.getReport(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}