package com.example.compositeservice.domain.request.EmplyeeForm;

import com.example.compositeservice.entity.EmployeeService.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OnBoardFormatRequest {
    private String firstName;
    private String lastName;
    private String middleName;
    private String preferredName;
    private String currentAddress;
    private String cellPhone;
    private String alternatePhone;
    private String gender;
    private String email;
    private String SSN;
    //Must be MM/DD/YYYY format
    private String dateOfBirth;

    private String startDate;
    private String endDate;

    private String driverLicense;
    private String driverLicenseExpiration;
    private String houseId;

    //Reference
    List<Contact> contact;
    List<Address> address;
    List<VisaStatus> visaStatus;
    List<PersonalDocument> personalDocument;
}
