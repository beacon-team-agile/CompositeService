package com.example.compositeservice.controller;

import com.example.compositeservice.domain.request.EmployeeService.VisaStatusUpdateRequest;
import com.example.compositeservice.domain.request.HousingService.FacilityReportDetailRequest;
import com.example.compositeservice.domain.request.HousingService.FacilityReportRequest;
import com.example.compositeservice.domain.response.EmployeeResponse.SingleEmployeeResponse;
import com.example.compositeservice.domain.response.HousingService.SingleFacilityReportDetailResponse;
import com.example.compositeservice.domain.response.HousingService.SingleFacilityReportResponse;
import com.example.compositeservice.domain.response.HousingService.SingleHouseResponse;
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

    @PostMapping("/updateVisaStatus")
    public SingleEmployeeResponse updateEmployeeVisaStatusById(@RequestParam String id,
                                                               @RequestBody VisaStatusUpdateRequest visaStatusUpdateRequest) {
        return compositeService.updateEmployeeVisaStatusById(id, visaStatusUpdateRequest);}
    
    @GetMapping("/personal_information_view")
    public SingleEmployeeResponse viewEmployeeInfo(@RequestPart String employeeId) {
    	return this.compositeService.getEmployeeById(employeeId);
    }
    
    @PatchMapping("/personal_information_fix")
    public SingleEmployeeResponse updateEmployeeInfo(@RequestParam String employeeId, @RequestBody Map<String, String> updateInfos) {
    	return this.compositeService.updateEmployeeInfoById(employeeId, updateInfos);
    }

    
    @GetMapping("/house/view-house-detail")
    public SingleHouseResponse viewHouseInfo(@RequestParam String employeeId) {
        return compositeService.getHouseByEmployeeId(employeeId);

    }
    
    @PostMapping("/house/report-house-detail")
    public SingleFacilityReportResponse createFacilityReport(@RequestBody FacilityReportRequest request) {
        return compositeService.createFacilityReport(request);

    }
    
    @PostMapping("house/report-house-detail/comment")
    public SingleFacilityReportDetailResponse createFacilityReportDetail(@RequestBody FacilityReportDetailRequest request) {
        return compositeService.createFacilityReportDetail(request);
    }

}
