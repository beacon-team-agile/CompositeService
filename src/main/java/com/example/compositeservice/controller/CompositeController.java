package com.example.compositeservice.controller;

import com.example.compositeservice.domain.response.EmployeeResponse.SingleEmployeeResponse;
import com.example.compositeservice.entity.EmployeeService.Employee;
import com.example.compositeservice.service.CompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("composite")
public class CompositeController {
    private CompositeService compositeService;

    @Autowired
    public void setCompositeService(CompositeService compositeService) {
        this.compositeService = compositeService;
    }

    //HR Only
    @GetMapping("employee/{id}")
    public SingleEmployeeResponse getEmployeeDetailById(@PathVariable String id) {
        return compositeService.getEmployeeById(id);
    }
}
