package com.example.report.service;

import org.springframework.stereotype.Service;

@Service("excelReportService")
public class ExcelReportService implements ReportService {

    @Override
    public String generateReport() {
        return "Excel Report Generated Successfully!";
    }
}