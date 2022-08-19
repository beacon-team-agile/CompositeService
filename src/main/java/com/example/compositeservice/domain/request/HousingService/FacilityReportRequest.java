package com.example.compositeservice.domain.request.HousingService;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class FacilityReportRequest {
    private String facilityId;

    private String employeeId;

    private String title;

    private String description;

    private String status;
}
