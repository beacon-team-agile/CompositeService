package com.example.compositeservice.domain.response.EmployeeResponse;

import com.example.compositeservice.domain.response.common.ResponseStatus;
import com.example.compositeservice.entity.EmployeeService.Employee;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class EmployeesResponse {
    private ResponseStatus responseStatus;

    private List<Employee> employees;
}
