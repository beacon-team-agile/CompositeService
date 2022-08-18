package com.example.compositeservice.controller;

import com.example.compositeservice.domain.request.ApplicationService.EmailApplicationStatusRequest;
import com.example.compositeservice.domain.response.ApplicationResponse.SingleApplicationWorkFlowResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.AllEmployeesBriefInfoResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.EmployeeBriefInfoResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.SingleEmployeeResponse;
import com.example.compositeservice.service.CompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/hr")
@PreAuthorize("hasAuthority('hr')")
public class HrController {
    private CompositeService compositeService;

    @Autowired
    public void setCompositeService(CompositeService compositeService) {
        this.compositeService = compositeService;
    }

    @GetMapping("/view-all-active-visa")
    public String viewAllActiveVisa(HttpServletResponse response) throws ParseException, IOException {

        return "Active visas";
    }

    @GetMapping("employee/all_brief_info")
    public AllEmployeesBriefInfoResponse getAllEmployeeBriefInfo() {
        return compositeService.getAllEmployeeBriefInfo();
    }


    @GetMapping("employee/{id}")
    public SingleEmployeeResponse getEmployeeDetailById(@PathVariable String id) {
        return compositeService.getEmployeeById(id);
    }

    @PostMapping("/applicationWorkFlow/email_result/{id}")
    public SingleApplicationWorkFlowResponse emailApplicationResultById(@PathVariable Integer id,
                                                                        @RequestBody EmailApplicationStatusRequest emailApplicationStatusRequest){
        return compositeService.emailApplicationResultById(id, emailApplicationStatusRequest);
    }

}
