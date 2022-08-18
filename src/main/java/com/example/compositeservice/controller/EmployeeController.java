package com.example.compositeservice.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping("/onboard")
    public String registerRequest(@RequestHeader(value = "Authorization", required = false) Optional<String> token,
                                  HttpServletResponse response) throws ParseException, IOException {
        if (!token.isPresent()) {
            return "No header provided";
        }

        return "On board page";
    }

    @GetMapping("/test")
    public String registerRequest(HttpServletResponse response) {

        return "test";
    }


}
