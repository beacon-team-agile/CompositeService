package com.example.compositeservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/employee")
@PreAuthorize("hasAuthority('employee')")
public class EmployeeController {

    @GetMapping("/onboard")
    public String viewOnBoardPage(HttpServletResponse response)
            throws ParseException, IOException {

        return "On board page";
    }


}
