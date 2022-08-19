package com.example.compositeservice.domain.response.EmployeeResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class EmployeeActiveVisaResponse {
    List<EmployeeActiveVisa> employeeActiveVisa;
}
