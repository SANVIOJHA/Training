package com.example.report.controller;

import com.example.report.service.ReportService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final Map<String, ReportService> reportServices;

    public ReportController(Map<String, ReportService> reportServices) {
        this.reportServices = reportServices;
    }

    @GetMapping("/{type}")
    public String generateReport(@PathVariable String type) {

        ReportService reportService = reportServices.get(type + "ReportService");

        if (reportService == null) {
            return "Invalid report type. Use 'pdf' or 'excel'.";
        }

        return reportService.generateReport();
    }
}