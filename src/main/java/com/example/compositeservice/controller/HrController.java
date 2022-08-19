package com.example.compositeservice.controller;

import com.example.compositeservice.domain.response.EmployeeResponse.EmployeeActiveVisaResponse;
import com.example.compositeservice.domain.response.common.ResponseStatus;
import com.example.compositeservice.entity.EmployeeService.Employee;
import com.example.compositeservice.entity.EmployeeService.VisaStatus;
import com.example.compositeservice.domain.request.DigitalDocumentUploadRequest;
import com.example.compositeservice.domain.response.ApplicationResponse.AddDigitalDocumentResponse;
import com.example.compositeservice.domain.response.ApplicationResponse.MultipleApplicationWorkFlowResponse;
import com.example.compositeservice.domain.request.ApplicationService.EmailApplicationStatusRequest;
import com.example.compositeservice.domain.request.AuthenticationService.TokenRequest;
import com.example.compositeservice.domain.response.ApplicationResponse.SingleApplicationWorkFlowResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.AllEmployeesBriefInfoResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.EmployeeBriefInfoResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.SingleEmployeeResponse;
import com.example.compositeservice.entity.EmployeeService.Employee;
import com.example.compositeservice.service.CompositeFileService;
import com.example.compositeservice.service.CompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.io.ByteArrayResource;
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

   
    
    @GetMapping("/view-specific-active-visa/{pageNo}")
    public List<VisaStatus> viewPagesActiveVisa(@PathVariable Integer pageNo, @RequestParam Integer pageSize) throws ParseException, IOException {
    	List<Employee> l = compositeService.paginatedEmployees(pageNo, pageSize).getEmployees();
        return l.stream().map(p->{
        	for(VisaStatus v : p.getVisaStatus()) {
        		if(v.getActiveFlag()) { return v; }
        	}return null;
        }).collect(Collectors.toList());
    }
    
    @GetMapping("/view-all-active-visa")
    public EmployeeActiveVisaResponse viewAllActiveVisa(HttpServletResponse response) {
        //Get all active visa
        return EmployeeActiveVisaResponse.builder()
                .employeeActiveVisa(compositeService.getAllActiveEmployee()).build();
    }

    //Hr viewing all employee
    @GetMapping("employee/all_brief_info")
    public AllEmployeesBriefInfoResponse getAllEmployeeBriefInfo() {
        return compositeService.getAllEmployeeBriefInfo();
    }


    //Hr viewing employee by id
    @GetMapping("/employee/{id}")
    public SingleEmployeeResponse getEmployeeDetailById(@PathVariable String id) {
        return compositeService.getEmployeeById(id);
    }
    
    @PostMapping(value = "upload_digital_file", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
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

    @PostMapping("/credential/generateToken_sendEmail")
    public ResponseEntity<String> generateToken_sendEmail(@RequestBody TokenRequest tokenRequest){
        String emailTokenURI = "http://localhost:8088/authentication-service/credential/generate";

        String accessToken = tokenRequest.getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);

        RequestEntity<TokenRequest> requestEntity = RequestEntity
                .post(emailTokenURI, tokenRequest)
                .headers(headers)
                .accept(MediaType.APPLICATION_JSON)
                .body(tokenRequest);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);

        return response;
    }

    @GetMapping("/applicationWorkFlow/all_inactive_application")
    public MultipleApplicationWorkFlowResponse getAllInactiveApplicationWorkFlow() {
        return compositeService.getAllInactiveApplicationWorkFlow();

    }
}
