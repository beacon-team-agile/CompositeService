package com.example.compositeservice.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping("/on-board")
    public String registerRequest(@RequestHeader(value = "Authorization", required = false) Optional<String> token,
                                  HttpServletResponse response) throws ParseException, IOException {
        if (!token.isPresent()) {
            System.out.println("No header provided");
        }

        return "On board page";
    }

}
