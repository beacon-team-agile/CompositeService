package com.example.compositeservice.domain.response.EmployeeResponse;

import com.example.compositeservice.domain.response.common.ResponseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmployeeDetailResponse {
    private ResponseStatus responseStatus;
    private String name;
    private String phone;
    private String email;
}
