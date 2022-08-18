package com.example.compositeservice.service;

import com.example.compositeservice.domain.response.EmployeeResponse.EmployeeDetailResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.SingleEmployeeResponse;
import com.example.compositeservice.entity.EmployeeService.Employee;
import com.example.compositeservice.service.remote.RemoteEmployeeService;
import com.sun.tools.javac.comp.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompositeService {
    private RemoteEmployeeService employeeService;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setEmployeeService(RemoteEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // TODO: 2022/8/17  
//    public EmployeeDetailResponse getEmployeeById(String id){
//        SingleEmployeeResponse employee = employeeService.GetEmployeeById(id);
//        if ()
//
//        return EmployeeDetailResponse
//    }
}
