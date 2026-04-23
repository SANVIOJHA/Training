package com.smartcourier.admin.service;

import com.smartcourier.admin.dto.AdminDashboardDTO;
import com.smartcourier.admin.dto.DeliveryResolutionRequest;
import com.smartcourier.admin.dto.DeliverySummaryDTO;
import com.smartcourier.admin.dto.HubDTO;
import com.smartcourier.admin.dto.ReportDTO;
import com.smartcourier.admin.dto.RoleUpdateDTO;
import com.smartcourier.admin.dto.UserSummaryDTO;
import com.smartcourier.admin.entity.Hub;
import com.smartcourier.admin.entity.Report;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AdminService {

    Report generateReport(Report report);

    List<Report> getAllReports();

    Report getReport(Long id);

    Report updateReport(Long id, ReportDTO dto);

    void deleteReport(Long id);

    AdminDashboardDTO getDashboard();

    List<Report> getReportsByType(String reportType);

    List<Report> getReportsByGeneratedBy(String generatedBy);

    List<Report> getReportsByDate(LocalDate reportDate);

    List<Report> getReportsByRange(LocalDate fromDate, LocalDate toDate);

    long countReports();

    Report getLatestReport();

    boolean reportExists(Long id);

    Map<String, Long> reportTypeSummary();

    List<DeliverySummaryDTO> getAllDeliveries();

    DeliverySummaryDTO resolveDeliveryException(Long id, DeliveryResolutionRequest request);

    List<UserSummaryDTO> getUsers(String authorization);

    UserSummaryDTO updateUserRole(Long id, RoleUpdateDTO request);

    void deleteUser(Long id);

    Hub createHub(HubDTO dto);

    List<Hub> getAllHubs();

    Hub updateHub(Long id, HubDTO dto);

    void deleteHub(Long id);
}
