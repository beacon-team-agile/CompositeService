package com.example.compositeservice.controller;

import com.example.compositeservice.domain.request.EmployeeService.VisaStatusUpdateRequest;
import com.example.compositeservice.domain.response.EmployeeResponse.SingleEmployeeResponse;
import com.example.compositeservice.service.CompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.compositeservice.service.CompositeFileService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/employee")
@PreAuthorize("hasAuthority('employee')")
public class EmployeeController {

    private CompositeService compositeService;


    @Autowired
    public void setCompositeService(CompositeService compositeService) {
        this.compositeService = compositeService;
    }

    @PostMapping("/updateVisaStatus")
    public SingleEmployeeResponse updateEmployeeVisaStatusById(@RequestParam String id,
                                                               @RequestBody VisaStatusUpdateRequest visaStatusUpdateRequest) {
        return compositeService.updateEmployeeVisaStatusById(id, visaStatusUpdateRequest);}


}
