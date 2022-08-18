package com.example.compositeservice.domain.response.EmployeeResponse;

import com.example.compositeservice.domain.response.common.ResponseStatus;
import com.example.compositeservice.entity.EmployeeService.Employee;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SingleEmployeeResponse {
    private ResponseStatus responseStatus;

    private Employee employee;

}
