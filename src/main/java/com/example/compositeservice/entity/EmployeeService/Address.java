package com.example.compositeservice.entity.EmployeeService;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Address {
	Integer id;
	
	String addressLine1;
	
	String addressLine2;
	
	String city;
	
	String state;
	
	String zipCode;

}
