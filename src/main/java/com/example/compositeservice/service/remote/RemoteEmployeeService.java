package com.example.compositeservice.service.remote;

import com.example.compositeservice.domain.response.EmployeeResponse.EmployeesResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.SingleEmployeeResponse;
import com.example.compositeservice.domain.response.common.ResponseStatus;

import feign.Headers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient("employee-service")
public interface RemoteEmployeeService {

    @GetMapping("employee-service/employee/{id}")
    SingleEmployeeResponse GetEmployeeById(@PathVariable String id);

    @GetMapping("employee-service/employee/all")
    EmployeesResponse GetAllEmployee();


    @PostMapping("employee-service/employee/document/upload")
    ResponseStatus UploadDocument(@RequestPart MultipartFile multifile, @RequestPart String filename);
    
    @PostMapping("employee-service/employee/document/upload_to_user")
    ResponseStatus UploadDocumentToUser(@RequestPart MultipartFile multifile
    		,@RequestPart String userid, @RequestPart String title, @RequestPart String comment);
    
    @GetMapping("employee-service/employee/document/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam String filename);


}
