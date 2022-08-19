package com.example.compositeservice.domain.request.HousingService;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class FacilityReportDetailRequest {
    private Integer facilityReportId;

    private String employeeId;

    private String comment;
}
