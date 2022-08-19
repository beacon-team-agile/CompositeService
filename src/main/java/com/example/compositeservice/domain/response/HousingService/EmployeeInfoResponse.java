package com.example.compositeservice.domain.response.HousingService;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmployeeInfoResponse {
    private String name;
    private String phone;
}
