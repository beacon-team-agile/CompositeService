package com.example.compositeservice.domain.response.EmployeeResponse;

import com.example.compositeservice.domain.response.common.ResponseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AllEmployeesBriefInfoResponse {
    ResponseStatus responseStatus;
    List<EmployeeBriefInfoResponse> employeeBriefInfoResponseList;
}
