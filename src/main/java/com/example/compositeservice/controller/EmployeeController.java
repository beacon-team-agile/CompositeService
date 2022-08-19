package com.example.compositeservice.controller;

import com.example.compositeservice.domain.request.EmployeeService.VisaStatusUpdateRequest;
import com.example.compositeservice.domain.response.EmployeeResponse.SingleEmployeeResponse;
import com.example.compositeservice.service.CompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.compositeservice.domain.request.EmplyeeForm.OnBoardFormatRequest;
import com.example.compositeservice.domain.response.EmployeeResponse.FilePathResponse;
import com.example.compositeservice.entity.EmployeeService.Employee;
import com.example.compositeservice.service.CompositeFileService;
import com.example.compositeservice.service.CompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/employee")
@PreAuthorize("hasAuthority('employee')")
public class EmployeeController {


    private CompositeService compositeService;
    private CompositeFileService compositeFileService;


    @Autowired
    public void setCompositeService(CompositeService compositeService) {
        this.compositeService = compositeService;
    }
    
    @Autowired
    public void setCompositeFileService(CompositeFileService compositeFileService) {
        this.compositeFileService = compositeFileService;
    }

    @GetMapping("/onboard")
    public String viewOnBoardPage(HttpServletResponse response)
            throws ParseException, IOException {

        return "On board page";
    }

    @PostMapping("/onboard")
    public String submitForm(OnBoardFormatRequest onBoardFormatRequest,
                                  @RequestPart MultipartFile multiFile) {

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
    
    @GetMapping("download")
    public ResponseEntity<ByteArrayResource> retrieveFile(@RequestPart String filename) {
        return compositeFileService.downloadDocument(filename);
    }
    
    @GetMapping("getAllDocuments/{empId}")
    public List<FilePathResponse> retrieveAllDocuments(@PathVariable String empId) {
        return compositeService.getFilePathList(empId);
    }

    @PostMapping("/updateVisaStatus")
    public SingleEmployeeResponse updateEmployeeVisaStatusById(@RequestParam String id,
                                                               @RequestBody VisaStatusUpdateRequest visaStatusUpdateRequest) {
        return compositeService.updateEmployeeVisaStatusById(id, visaStatusUpdateRequest);
    }

}
