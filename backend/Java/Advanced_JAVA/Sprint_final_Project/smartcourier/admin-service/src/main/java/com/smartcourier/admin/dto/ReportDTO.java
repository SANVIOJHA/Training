package com.smartcourier.admin.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ReportDTO {
    private String reportType;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String generatedBy;
}