package com.example.compositeservice.domain.response.HousingResponse;

import com.example.compositeservice.domain.response.common.ResponseStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class HousingEmployeeResponse {
    private ResponseStatus responseStatus;
    private String address;
    private List<EmployeeInfoResponse> employeeList;
}
