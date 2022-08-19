package com.example.compositeservice.service;

import com.example.compositeservice.domain.request.ApplicationService.EmailApplicationStatusRequest;
import com.example.compositeservice.domain.request.HousingService.FacilityReportDetailRequest;
import com.example.compositeservice.domain.request.HousingService.FacilityReportRequest;
import com.example.compositeservice.domain.response.ApplicationResponse.SingleApplicationWorkFlowResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.AllEmployeesBriefInfoResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.EmployeeBriefInfoResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.EmployeesResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.SingleEmployeeResponse;
import com.example.compositeservice.domain.response.HousingResponse.*;
import com.example.compositeservice.domain.response.common.ResponseStatus;
import com.example.compositeservice.entity.EmployeeService.Employee;
import com.example.compositeservice.entity.HousingService.*;
import com.example.compositeservice.service.remote.RemoteEmployeeService;
import com.example.compositeservice.service.remote.RemoteHousingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class CompositeService {
    private RemoteEmployeeService employeeService;
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
    public void setHousingService(RemoteHousingService housingService) {
        this.housingService = housingService;
    }

    public AllEmployeesBriefInfoResponse getAllEmployeeBriefInfo() {
        EmployeesResponse employeeList = employeeService.GetAllEmployee();

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
        return employeeService.GetEmployeeById(id);
    }


    public void addEmployeeForm(Employee employee, @RequestPart MultipartFile multiFile) {
        //Add employee
        Integer id = employeeService.AddEmployee(employee);

        //Add files
        employeeService.UploadNewDocumentToUser(multiFile, id);
    }

    public HousingEmployeeResponse getEmployeeHouseDetail(@PathVariable String employeeId) {
        SingleEmployeeResponse employee = employeeService.GetEmployeeById(employeeId);
        Integer houseId = employee.getEmployee().getHouseId();
        SingleHouseResponse house = housingService.getHouseById(houseId);

        EmployeesResponse employeeList = employeeService.GetAllEmployee();
        return null;
        }

    public HousingHRResponse getHRHouseDetail(@PathVariable Integer houseId) {
        SingleHouseResponse house = housingService.getHouseById(houseId);

        return HousingHRResponse.builder()
                .responseStatus(ResponseStatus.builder()
                        .is_success(true)
                        .message("View HR House Detail Successfully!")
                        .build())
                .address(house.getHouse().getAddress())
                .landlord(housingService.getLandlordByHouseId(houseId).getFirstName() + " "
                        + housingService.getLandlordByHouseId(houseId).getLastName())
                .cellPhone(housingService.getLandlordByHouseId(houseId).getCellPhone())
                .email(housingService.getLandlordByHouseId(houseId).getEmail())
                .occupantNumber(housingService.getHouseById(houseId).getHouse().getMaxOccupant())
                .build();

    }

    public SingleFacilityReportResponse createFacilityReport(FacilityReportRequest request) {

        return housingService.addFacilityReport(request);

    }

    public SingleFacilityReportDetailResponse createFacilityReportDetail(@RequestBody FacilityReportDetailRequest request) {

        housingService.addFacilityReportDetail(request);
        return SingleFacilityReportDetailResponse.builder()
                .responseStatus(ResponseStatus.builder()
                        .is_success(true)
                        .message("Create Facility Report Detail Successfully!")
                        .build())
                .facilityReportDetail(FacilityReportDetail.builder()
                        .facilityReportId(request.getFacilityReportId())
                        .employeeId(request.getEmployeeId())
                        .comment(request.getComment())
                        .createDate(Calendar.getInstance().getTime())
                        .build())
                .build();

    }

}
