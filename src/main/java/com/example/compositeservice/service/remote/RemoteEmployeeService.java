package com.example.compositeservice.service.remote;

import com.example.compositeservice.domain.response.EmployeeResponse.SingleEmployeeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("employee-service")
public interface RemoteEmployeeService {

    @GetMapping("employee-service/employee/{id}")
    SingleEmployeeResponse GetEmployeeById(@PathVariable String id);

}
