package com.example.compositeservice.domain.response.EmployeeResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmployeeBriefInfoResponse {
    private String name;
    private String phone;
    private String email;
}
