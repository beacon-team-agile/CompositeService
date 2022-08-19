package com.example.compositeservice.domain.request.EmployeeService;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VisaStatusUpdateRequest {
    private String visaStatus;
    private String startDate;
    private String endDate;
}
