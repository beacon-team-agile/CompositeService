package com.example.compositeservice.domain.response.EmployeeResponse;

import com.example.compositeservice.entity.EmployeeService.VisaStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class EmployeeActiveVisa {
    private String employeeFullName;
    private List<VisaStatusInfo> workAuthorizationType;
}
