package com.smartcourier.admin.dto;


import lombok.Data;

@Data
public class AdminDashboardDTO {

    private int totalDeliveries;
    private int shipped;
    private int delivered;
    private int cancelled;
    private int delayed;
    private int failed;
    private int activeHubs;
    private long totalUsers;
}
