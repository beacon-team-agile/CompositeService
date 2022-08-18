package com.example.compositeservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/employee")
@PreAuthorize("hasAuthority('employee')")
public class EmployeeController {

    @GetMapping("/onboard")
    public String viewOnBoardPage(HttpServletResponse response) throws ParseException, IOException {

        return "On board page";
    }

    @GetMapping("/main_menu")
    public String viewMainPage(HttpServletResponse response) {
        //Identify by header

        return "Welcome to homepage";
    }


}
