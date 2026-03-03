package com.example.report.service;

import org.springframework.stereotype.Service;

@Service("pdfReportService")
public class PdfReportService implements ReportService {

    @Override
    public String generateReport() {
        return "PDF Report Generated Successfully!";
    }
}