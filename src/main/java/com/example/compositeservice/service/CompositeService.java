package com.example.compositeservice.service;

import com.example.compositeservice.domain.response.EmployeeResponse.AllEmployeesBriefInfoResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.EmployeeBriefInfoResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.EmployeesResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.SingleEmployeeResponse;
import com.example.compositeservice.domain.response.common.ResponseStatus;
import com.example.compositeservice.entity.EmployeeService.Employee;
import com.example.compositeservice.service.remote.RemoteEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompositeService {
    private RemoteEmployeeService employeeService;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setEmployeeService(RemoteEmployeeService employeeService) {
        this.employeeService = employeeService;
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
}
