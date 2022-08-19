package com.example.compositeservice.controller;

import com.example.compositeservice.domain.request.EmployeeForm.OnBoardFormatRequest;
import com.example.compositeservice.domain.request.EmployeeService.VisaStatusUpdateRequest;
import com.example.compositeservice.domain.response.EmployeeResponse.SingleEmployeeResponse;
import com.example.compositeservice.entity.EmployeeService.Employee;
import com.example.compositeservice.service.CompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.compositeservice.service.CompositeFileService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("/employee")
@PreAuthorize("hasAuthority('employee')")
public class EmployeeController {

    private CompositeService compositeService;


    @Autowired
    public void setCompositeService(CompositeService compositeService) {
        this.compositeService = compositeService;
    }
    
    @GetMapping("/personal_information_view")
    public SingleEmployeeResponse viewEmployeeInfo(@RequestPart String employeeId) {
    	return this.compositeService.getEmployeeById(employeeId);
    }
    
    

    @PostMapping("/updateVisaStatus")
    public SingleEmployeeResponse updateEmployeeVisaStatusById(@RequestParam String id,
                                                               @RequestBody VisaStatusUpdateRequest visaStatusUpdateRequest) {
        return compositeService.updateEmployeeVisaStatusById(id, visaStatusUpdateRequest);}

    
    @PatchMapping("/personal_information_fix")
    public SingleEmployeeResponse updateEmployeeInfo(@RequestParam String employeeId, @RequestBody Map<String, String> updateInfos) {
    	return this.compositeService.updateEmployeeInfoById(employeeId, updateInfos);
    }
    
    

}
