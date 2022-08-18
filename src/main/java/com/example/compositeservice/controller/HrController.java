package com.example.compositeservice.controller;

import com.example.compositeservice.domain.request.DigitalDocumentUploadRequest;
import com.example.compositeservice.domain.response.ApplicationResponse.AddDigitalDocumentResponse;
import com.example.compositeservice.domain.response.ApplicationResponse.MultipleApplicationWorkFlowResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.AllEmployeesBriefInfoResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.EmployeeBriefInfoResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.SingleEmployeeResponse;
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

@RestController
@RequestMapping("/hr")
@PreAuthorize("hasAuthority('hr')")
public class HrController {
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
    
    @PostMapping("upload_digital_file")
    public AddDigitalDocumentResponse uploadDocument(@RequestPart("file") MultipartFile file
    		, @RequestPart String title, @RequestPart String description, @RequestPart String is_required) {
        return compositeFileService.uploadDigitalDocument(DigitalDocumentUploadRequest
        		.builder()
        		.multifile(file)
        		.title(title)
        		.description(description)
        		.is_required(Boolean.parseBoolean(is_required))
        		.build());
    }
    
    @GetMapping("download/{filename}")
    public ResponseEntity<ByteArrayResource> retrieveFile(@PathVariable String filename) {
        return compositeFileService.downloadDocument(filename);
    }
    
}
