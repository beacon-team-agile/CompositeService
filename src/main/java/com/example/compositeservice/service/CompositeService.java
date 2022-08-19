package com.example.compositeservice.service;

import com.example.compositeservice.domain.response.ApplicationResponse.MultipleApplicationWorkFlowResponse;
import com.example.compositeservice.domain.request.ApplicationService.EmailApplicationStatusRequest;
import com.example.compositeservice.domain.request.EmployeeService.VisaStatusUpdateRequest;
import com.example.compositeservice.domain.request.HousingService.FacilityReportDetailRequest;
import com.example.compositeservice.domain.request.HousingService.FacilityReportRequest;
import com.example.compositeservice.domain.response.ApplicationResponse.SingleApplicationWorkFlowResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.*;
import com.example.compositeservice.domain.response.HousingService.SingleFacilityReportDetailResponse;
import com.example.compositeservice.domain.response.HousingService.SingleFacilityReportResponse;
import com.example.compositeservice.domain.response.HousingService.SingleHouseResponse;
import com.example.compositeservice.domain.response.common.ResponseStatus;
import com.example.compositeservice.entity.EmployeeService.Employee;
import com.example.compositeservice.entity.EmployeeService.PersonalDocument;
import com.example.compositeservice.entity.EmployeeService.VisaStatus;
import com.example.compositeservice.entity.HousingService.FacilityReportDetail;
import com.example.compositeservice.service.remote.RemoteApplicationService;
import com.example.compositeservice.service.remote.RemoteEmployeeService;
import com.example.compositeservice.service.remote.RemoteHousingService;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CompositeService {
	
	private final String AES_KEY = "securedEndpoint123";
	private final String AES_SALT = "414243444546";
	
	private final String DOWNLOAD_PREFIX = "localhost:9000/composite-service/employee/download/";
    private RemoteEmployeeService employeeService;
    private RemoteApplicationService applicationService;
    private RemoteHousingService housingService;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setEmployeeService(RemoteEmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    @Autowired
    public void setApplicationService(RemoteApplicationService applicationService) {
        this.applicationService = applicationService;
    }
    
    @Autowired
    public void setHousingService(RemoteHousingService housingService) {
        this.housingService = housingService;
    }

    public AllEmployeesBriefInfoResponse getAllEmployeeBriefInfo() {
        EmployeesResponse employeeList = employeeService.getAllEmployee();

        List<EmployeeBriefInfoResponse> employeeBriefInfoResponseList = new ArrayList<>();
        String name;
        String phone;
        String email;

        for(Employee employee: employeeList.getEmployees()) {
            if(employee.getPreferredName()!=null &&
                    !employee.getPreferredName().equals("")){
                name = employee.getPreferredName() + " " + employee.getLastName();
            }else {
                name = employee.getFirstName() + " " + employee.getLastName();
            }
            phone = employee.getCellPhone();
            email = employee.getEmail();
            EmployeeBriefInfoResponse current_response = EmployeeBriefInfoResponse.builder()
                    .name(name)
                    .phone(phone)
                    .email(email)
                    .build();
            employeeBriefInfoResponseList.add(current_response);
        }

        return AllEmployeesBriefInfoResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .is_success(true)
                                .message("Successfully found All Employees")
                                .build()
                )
                .employeeBriefInfoResponseList(employeeBriefInfoResponseList)
                .build();
    }


    public SingleEmployeeResponse getEmployeeById(String id){
        return employeeService.getEmployeeById(id);
    }
    
    public SingleEmployeeResponse updateEmployeeInfoById(String id,
    		Map<String, String> infos) {
	    	SingleEmployeeResponse ser = getEmployeeById(id);
	    	if(ser.getResponseStatus().is_success()) {
	    		Employee e = ser.getEmployee();
	    		e.setFirstName(infos.getOrDefault("firstName", e.getFirstName()));
	    		e.setLastName(infos.getOrDefault("lastNwame", e.getLastName()));
	    		e.setMiddleName(infos.getOrDefault("middleName", e.getMiddleName()));
	    		e.setPreferredName(infos.getOrDefault("preferredName", e.getPreferredName()));
	    		e.setCellPhone(infos.getOrDefault("cellPhone", e.getCellPhone()));
	    		e.setAlternatePhone(infos.getOrDefault("alternatePhone", e.getAlternatePhone()));
	    		e.setGender(infos.getOrDefault("gender", e.getGender()));
	    		e.setEmail(infos.getOrDefault("email", e.getEmail()));
	    		e.setSsn(infos.getOrDefault("ssn", e.getSsn()));
	    		e.setDob(infos.getOrDefault("dob", e.getDob()));
	    		e.setDriverLicense(infos.getOrDefault("driverLicense", e.getDriverLicense()));
	    		e.setDriverLicenseExpiration(infos.getOrDefault("driverLicenseExpiration", e.getDriverLicenseExpiration()));
	    		e.setStartDate(infos.getOrDefault("startDate", e.getStartDate()));
	    		e.setEndDate(infos.getOrDefault("endDate", e.getEndDate()));
	    		return employeeService.updateEmployeeById(id, e);
	    		}
	    	else {
	    		return SingleEmployeeResponse.builder().responseStatus(ResponseStatus.builder().is_success(false).build()).build();
	    	}
    }

    public SingleApplicationWorkFlowResponse emailApplicationResultById(@PathVariable Integer id,
                                                                        @RequestBody EmailApplicationStatusRequest emailApplicationStatusRequest){
        return applicationService.emailApplicationResultById(id,emailApplicationStatusRequest);
    }

    public SingleEmployeeResponse updateEmployeeVisaStatusById(@RequestParam String id,
                                                               @RequestBody VisaStatusUpdateRequest visaStatusUpdateRequest) {
        return employeeService.updateEmployeeVisaStatusById(id, visaStatusUpdateRequest);
    }

    
    public SingleEmployeeResponse addEmployee(Employee employee) {
    	return employeeService.AddEmployee(employee);
    }
    
    public EmployeesResponse paginatedEmployees(Integer pageNo, Integer pageSize) {
    	return this.employeeService.GetEmployeeList(pageNo, pageSize);
    }

    public ResponseStatus addEmployeeForms(String employeeId, MultipartFile[] multiFiles) {
        //Add employee
        SingleEmployeeResponse emp = employeeService.getEmployeeById(employeeId);
        if(!emp.getResponseStatus().is_success()) {
        	return emp.getResponseStatus();
        }
        for(int i = 0;i < multiFiles.length;i++) {
            ResponseStatus rs = employeeService.UploadDocumentToUser(multiFiles[i], emp.getEmployee().getId(), multiFiles[i].getOriginalFilename(), "Initial Upload");
            if(!rs.is_success()) return rs;
        }
        return ResponseStatus.builder().is_success(true).message("Uploads all succeeded").build();
    }
    
    public List<FilePathResponse> getFilePathList(String employeeId){
    	List<PersonalDocument> originalList = getEmployeeById(employeeId).getEmployee().getPersonalDocument();
    	TextEncryptor textEnc = Encryptors.text(AES_KEY, AES_SALT);
    	return originalList.stream().map(d->{ return FilePathResponse.builder()
    			.title(d.getTitle())
    			.comment(d.getComment())
    			.path(DOWNLOAD_PREFIX + textEnc.encrypt(d.getPath()))
    			.build(); })
    			.collect(Collectors.toList());
    }


    public MultipleApplicationWorkFlowResponse getAllInactiveApplicationWorkFlow(){
        return applicationService.getAllInactiveApplicationWorkFlow();

}
    public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));

    }

    public List<EmployeeActiveVisa> getAllActiveEmployee() {
        List<Employee> employeeList = employeeService.getAllEmployee().getEmployees();
        List<EmployeeActiveVisa> filteredEmployee = new ArrayList<>();

        for (Employee employee: employeeList) {
            List<VisaStatus> visaStatusList = employee.getVisaStatus();
            String fullName = employee.getFirstName() + employee.getLastName();
            List<VisaStatusInfo> visaStatusInfoList = new ArrayList<>();
            for (VisaStatus visaStatus : visaStatusList) {
                if (visaStatus.getActiveFlag()) {
                    String remainingDays = "";
                    try {
                        Date endDate =new SimpleDateFormat("MM/dd/yyyy").parse(visaStatus.getEndDate());
                        Date startDate =new SimpleDateFormat("MM/dd/yyyy").parse(visaStatus.getStartDate());
                        remainingDays = "" + daysBetween(startDate, endDate);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    VisaStatusInfo visaStatusInfo =
                            VisaStatusInfo.builder()
                            .visaType(visaStatus.getVisaType())
                            .expirationDate(visaStatus.getEndDate())
                            .dayLeft(remainingDays).build();

                    visaStatusInfoList.add(visaStatusInfo);
                }
            }

            if (visaStatusInfoList.size() != 0) {
                filteredEmployee.add(EmployeeActiveVisa.builder()
                        .employeeFullName(fullName)
                        .workAuthorizationType(visaStatusInfoList)
                        .build());
            }
        }
        return filteredEmployee;
    }
    
    public SingleFacilityReportResponse createFacilityReport(FacilityReportRequest request) {

        return housingService.addFacilityReport(request);

    }
    
    public SingleFacilityReportDetailResponse createFacilityReportDetail(FacilityReportDetailRequest request) {

        return housingService.addFacilityReportDetail(request);

    }
    
    public SingleHouseResponse getHouseById(Integer houseId) {
    	return housingService.getHouseById(houseId);
    }
    
    public SingleHouseResponse getHouseByEmployeeId(String employeeId) {
    	Integer hid = getEmployeeById(employeeId).getEmployee().getHouseId();
    	return housingService.getHouseById(hid);
    }
    
    
}
