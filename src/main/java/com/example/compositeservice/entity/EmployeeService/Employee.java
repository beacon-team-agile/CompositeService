package com.example.compositeservice.entity.EmployeeService;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Employee {
	String id;
	
	Integer userId;
	
	String firstName;
	
	String lastName;
	
	String middleName;
	
	String preferredName;
	
	String email;
	
	String cellPhone;
	
	String alternatePhone;
	
	String gender;
	
	String ssn;
	
	String dob;

	String startDate;

	String endDate;
	
	String driverLicense;

	String driverLicenseExpiration;
	
	Integer houseId;
	
	List<Contact> contact;

	List<Address> address;
	
	List<VisaStatus> visaStatus;

	List<PersonalDocument> personalDocument;
}
