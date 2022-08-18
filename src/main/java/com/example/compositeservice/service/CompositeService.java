package com.example.compositeservice.service;

import com.example.compositeservice.domain.response.EmployeeResponse.EmployeeDetailResponse;
import com.example.compositeservice.domain.response.EmployeeResponse.SingleEmployeeResponse;
import com.example.compositeservice.domain.response.common.ResponseStatus;
import com.example.compositeservice.entity.EmployeeService.Employee;
import com.example.compositeservice.service.remote.RemoteEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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


    public EmployeeDetailResponse getEmployeeById(String id){
        SingleEmployeeResponse employee = employeeService.GetEmployeeById(id);
        String name;
        String phone;
        String email;

        if (employee.getEmployee().getPreferredName()!=null &&
                !employee.getEmployee().getPreferredName().equals("")) {
            name = employee.getEmployee().getPreferredName() + employee.getEmployee().getLastName();
        }else {
            name = employee.getEmployee().getFirstName() + employee.getEmployee().getLastName();
        }

        phone = employee.getEmployee().getCellPhone();
        email = employee.getEmployee().getEmail();

        return EmployeeDetailResponse.builder()
                .responseStatus(
                        ResponseStatus.builder()
                                .is_success(true)
                                .message("Successfully found Employee")
                                .build()
                )
                .name(name)
                .phone(phone)
                .email(email)
                .build();
    }
}
