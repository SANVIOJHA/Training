package com.smartcourier.admin.service;

import com.smartcourier.admin.dto.AdminDashboardDTO;
import com.smartcourier.admin.entity.Report;

import java.util.List;

public interface AdminService {

    Report generateReport(Report report);

    List<Report> getAllReports();

    Report getReport(Long id);

    void deleteReport(Long id);

    AdminDashboardDTO getDashboard();
}