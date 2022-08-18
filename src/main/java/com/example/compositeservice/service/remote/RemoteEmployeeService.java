package com.example.compositeservice.service.remote;

import com.example.compositeservice.config.FeignEncoderConfig;
import com.example.compositeservice.domain.response.EmployeeResponse.EmployeesResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.SingleEmployeeResponse;
import com.example.compositeservice.domain.response.common.ResponseStatus;

import feign.Headers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.compositeservice.entity.EmployeeService.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "employee-service", configuration = FeignEncoderConfig.class)
public interface RemoteEmployeeService {

    @GetMapping("employee-service/employee/{id}")
    SingleEmployeeResponse getEmployeeById(@PathVariable String id);

    @GetMapping("employee-service/employee/all")
    EmployeesResponse getAllEmployee();

    @PostMapping("employee-service/employee/add")
    SingleEmployeeResponse AddEmployee(Employee employee);
    

    @PostMapping("employee-service/employee/update/{id}")
    SingleEmployeeResponse updateEmployeeById(@PathVariable String id, @RequestBody Employee employee);

    @PostMapping(value = "employee-service/employee/document/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseStatus UploadDocument(@RequestPart MultipartFile multifile, @RequestPart String filename);
    
    @PostMapping(value = "employee-service/employee/document/upload_to_user", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseStatus UploadDocumentToUser(@RequestPart MultipartFile multifile
    		,@RequestPart String userid, @RequestPart String title, @RequestPart String comment);
    
    @GetMapping("employee-service/employee/document/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam String filename);
}
