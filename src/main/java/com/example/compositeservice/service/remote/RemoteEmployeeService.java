package com.example.compositeservice.service.remote;

import com.example.compositeservice.domain.response.EmployeeResponse.EmployeesResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.SingleEmployeeResponse;
import com.example.compositeservice.entity.EmployeeService.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient("employee-service")
public interface RemoteEmployeeService {

    @GetMapping("employee-service/employee/{id}")
    SingleEmployeeResponse getEmployeeById(@PathVariable String id);

    @GetMapping("employee-service/employee/all")
    EmployeesResponse getAllEmployee();

    @PostMapping("employee-service/employee/add")
    Integer AddEmployee(Employee employee);

    @PostMapping("employee-service/employee/document/upload_to_user/{userId}")
    void UploadNewDocumentToUser(@RequestPart MultipartFile multiFile, @PathVariable Integer userId);
}
