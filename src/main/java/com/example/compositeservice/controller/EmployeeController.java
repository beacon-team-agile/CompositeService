package com.example.compositeservice.controller;

import com.example.compositeservice.domain.request.EmplyeeForm.OnBoardFormatRequest;
import com.example.compositeservice.domain.request.HousingService.FacilityReportDetailRequest;
import com.example.compositeservice.domain.request.HousingService.FacilityReportRequest;
import com.example.compositeservice.domain.response.HousingResponse.SingleFacilityReportDetailResponse;
import com.example.compositeservice.domain.response.HousingResponse.SingleFacilityReportResponse;
import com.example.compositeservice.entity.EmployeeService.Employee;
import com.example.compositeservice.service.CompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/onboard")
    public String submitForm(OnBoardFormatRequest onBoardFormatRequest,
                                  @RequestPart MultipartFile multiFile,
                                  HttpServletResponse response)
            throws ParseException, IOException {

        //Add new employee data
        Employee newEmployee = Employee.builder()
                .firstName(onBoardFormatRequest.getFirstName())
                .lastName(onBoardFormatRequest.getLastName())
                .middleName(onBoardFormatRequest.getMiddleName())
                .preferredName(onBoardFormatRequest.getPreferredName())
                .email(onBoardFormatRequest.getEmail())
                .cellPhone(onBoardFormatRequest.getCellPhone())
                .alternatePhone(onBoardFormatRequest.getAlternatePhone())
                .gender(onBoardFormatRequest.getGender())
                .ssn(onBoardFormatRequest.getSSN())
                .dob(onBoardFormatRequest.getDateOfBirth())
                .startDate(onBoardFormatRequest.getStartDate())
                .endDate(onBoardFormatRequest.getEndDate())
                .driverLicense(onBoardFormatRequest.getDriverLicense())
                .driverLicenseExpiration(onBoardFormatRequest.getDriverLicenseExpiration())
                .houseId(Integer.valueOf(onBoardFormatRequest.getHouseId()))
                .contact(onBoardFormatRequest.getContact())
                .address(onBoardFormatRequest.getAddress())
                .visaStatus(onBoardFormatRequest.getVisaStatus())
                .personalDocument(onBoardFormatRequest.getPersonalDocument())
        .build();

        this.compositeService.addEmployeeForm(newEmployee, multiFile);

        return "On board page";
    }

    @GetMapping("/main_menu")
    public String viewMainPage(HttpServletResponse response) {
        //Identify by header

        return "Welcome to homepage";
    }

    @PostMapping("/house/report-house-detail")
    public SingleFacilityReportResponse createFacilityReport(@RequestBody FacilityReportRequest request) {
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
