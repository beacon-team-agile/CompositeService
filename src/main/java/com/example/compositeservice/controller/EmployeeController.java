package com.example.compositeservice.controller;

import com.example.compositeservice.domain.request.HousingService.FacilityReportDetailRequest;
import com.example.compositeservice.domain.request.HousingService.FacilityReportRequest;
import com.example.compositeservice.domain.response.HousingResponse.SingleFacilityReportDetailResponse;
import com.example.compositeservice.domain.response.HousingResponse.SingleFacilityReportResponse;

import com.example.compositeservice.service.CompositeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/onboard")
    public String viewOnBoardPage(HttpServletResponse response)
            throws ParseException, IOException {

        return "On board page";
    }


    @GetMapping("/main_menu")
    public String viewMainPage(HttpServletResponse response) {
        //Identify by header

        return "Welcome to homepage";
    }

    @PostMapping("/house/report-house-detail")
    public SingleFacilityReportResponse createFacilityReport(@RequestBody FacilityReportRequest request) {
        System.out.println("1");
        return compositeService.createFacilityReport(request);

    }

    @PostMapping("house/report-house-detail/comment")
    public SingleFacilityReportDetailResponse createFacilityReportDetail(@RequestBody FacilityReportDetailRequest request) {
        return compositeService.createFacilityReportDetail(request);
    }

//    @GetMapping("/house/view-house-detail/{houseId}")
//    public HousingEmployeeResponse getEmployeeHouseDetail(@PathVariable Integer houseId) {
//        return compositeService.getEmployeeHouseDetail(houseId);
//    }

}
