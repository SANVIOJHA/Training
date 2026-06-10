package com.smartcourier.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusUpdateDTO {
    private String status;
    private String location;
    private String description;
    private Long currentHubId;
}
