package com.example.compositeservice.service.remote;

import com.example.compositeservice.domain.request.EmployeeService.VisaStatusUpdateRequest;
import com.example.compositeservice.domain.response.EmployeeResponse.EmployeesResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.SingleEmployeeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("employee-service")
public interface RemoteEmployeeService {

    @GetMapping("employee-service/employee/{id}")
    SingleEmployeeResponse getEmployeeById(@PathVariable String id);

    @GetMapping("employee-service/employee/all")
    EmployeesResponse getAllEmployee();

    @PostMapping("employee-service/employee/updateVisaStatus")
    SingleEmployeeResponse updateEmployeeVisaStatusById(@RequestParam String id,
                                                        @RequestBody VisaStatusUpdateRequest visaStatusUpdateRequest);

}
