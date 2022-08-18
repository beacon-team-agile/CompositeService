package com.example.compositeservice.controller;

import com.example.compositeservice.domain.response.EmployeeResponse.EmployeeDetailResponse;
import com.example.compositeservice.service.CompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;

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

    @GetMapping("employee/{id}")
    public EmployeeDetailResponse getEmployeeDetailById(@PathVariable String id) {
        return compositeService.getEmployeeById(id);
    }
}
