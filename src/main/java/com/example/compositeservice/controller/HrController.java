package com.example.compositeservice.controller;

import com.example.compositeservice.domain.request.DigitalDocumentUploadRequest;
import com.example.compositeservice.domain.request.ApplicationService.EmailApplicationStatusRequest;
import com.example.compositeservice.domain.request.HousingService.FacilityReportDetailRequest;
import com.example.compositeservice.domain.request.HousingService.FacilityReportRequest;
import com.example.compositeservice.domain.response.ApplicationResponse.AddDigitalDocumentResponse;
import com.example.compositeservice.domain.response.ApplicationResponse.MultipleApplicationWorkFlowResponse;
import com.example.compositeservice.domain.response.ApplicationResponse.SingleApplicationWorkFlowResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.AllEmployeesBriefInfoResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.SingleEmployeeResponse;

import com.example.compositeservice.entity.EmployeeService.Employee;
import com.example.compositeservice.service.CompositeFileService;

import com.example.compositeservice.domain.response.HousingResponse.HousingHRResponse;
import com.example.compositeservice.domain.response.HousingResponse.SingleFacilityReportDetailResponse;
import com.example.compositeservice.domain.response.HousingResponse.SingleFacilityReportResponse;

import com.example.compositeservice.service.CompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

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
        return compositeService.getEmployeeById(id);}
    
    @PostMapping(value = "upload_digital_file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    		)
    public AddDigitalDocumentResponse uploadDocument(@RequestPart("file") MultipartFile multifile
    		, @RequestPart String title, @RequestPart String description, @RequestPart String is_required) {
    	
        return compositeFileService.uploadDigitalDocument(DigitalDocumentUploadRequest
        		.builder()
        		.multifile(multifile)
        		.title(title)
        		.description(description)
        		.is_required(Boolean.parseBoolean(is_required))
        		.build());
    }
    
    @PostMapping(value = "upload_digital_files", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String[]> uploadDocument(@RequestPart("file") MultipartFile[] multifiles
    		, @RequestPart String title, @RequestPart String description, @RequestPart String is_required) {
    	Boolean b = true;
    	String[] sArr = new String[multifiles.length];
    	for(int i = 0; i < multifiles.length; i++) {
    		AddDigitalDocumentResponse a = compositeFileService.uploadDigitalDocument(DigitalDocumentUploadRequest
            		.builder()
            		.multifile(multifiles[i])
            		.title("file" + i + "_" + title)
            		.description(description)
            		.is_required(Boolean.parseBoolean(is_required))
            		.build());
    		if(!a.getResponseStatus().is_success()) b = false;
    		sArr[i] = a.getDigitalDocument().getPath();
    	}
    	if(b) {
        	ResponseEntity<String[]> res = new ResponseEntity<String[]>(sArr, HttpStatus.ACCEPTED);
        	return res;
    	}
        else {
        	ResponseEntity<String[]> res = new ResponseEntity<String[]>(sArr, HttpStatus.INTERNAL_SERVER_ERROR);
        	return res;
        }
    }
    
    @GetMapping("download/{filename}")
    public ResponseEntity<ByteArrayResource> retrieveFile(@PathVariable String filename) {
        return compositeFileService.downloadDocumentEncryptedKey(filename);
    }
   

    @PostMapping("/applicationWorkFlow/email_result/{id}")
    public SingleApplicationWorkFlowResponse emailApplicationResultById(@PathVariable Integer id,
                                                                        @RequestParam String employee_id,
                                                                        @RequestBody EmailApplicationStatusRequest emailApplicationStatusRequest){
        SingleEmployeeResponse employeeResponse = compositeService.getEmployeeById(employee_id);

        Employee employee = employeeResponse.getEmployee();

        int user_id = employee.getUserId();
        System.out.println(user_id);

        String URI;
        boolean is_approved = emailApplicationStatusRequest.getApproved();
        if (is_approved) {
            //enable user by user id
            URI = "http://localhost:9000/authentication-service/credential/enableEmployee";
        }else {
            //disable user by user id
            URI = "http://localhost:9000/authentication-service/credential/disableEmployee";
        }

        String accessToken = emailApplicationStatusRequest.getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity request = new HttpEntity(headers);

        Map<String,Integer> params = new HashMap<>();
        params.put("userId",user_id);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URI)
                .queryParam("userId",user_id);


        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(builder.buildAndExpand(params).toUri(),HttpMethod.POST,request,void.class);

        return compositeService.emailApplicationResultById(id, emailApplicationStatusRequest);
    }


    @GetMapping("house/view-house-detail/{houseId}")
    public HousingHRResponse getHRHouseDetail(@PathVariable Integer houseId) {
        return compositeService.getHRHouseDetail(houseId);
    }

    @PostMapping("house/report-house-detail/comment")
    public SingleFacilityReportDetailResponse createFacilityReportDetail(@RequestBody FacilityReportDetailRequest request) {
        return compositeService.createFacilityReportDetail(request);
    }

    @GetMapping("/applicationWorkFlow/all_inactive_application")
    public MultipleApplicationWorkFlowResponse getAllInactiveApplicationWorkFlow() {
        return compositeService.getAllInactiveApplicationWorkFlow();

    }
}
