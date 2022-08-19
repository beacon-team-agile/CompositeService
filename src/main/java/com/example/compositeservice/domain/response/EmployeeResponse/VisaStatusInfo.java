package com.example.compositeservice.domain.response.EmployeeResponse;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class VisaStatusInfo {
    private String expirationDate;
    private String dayLeft;
    private String visaType;
}
