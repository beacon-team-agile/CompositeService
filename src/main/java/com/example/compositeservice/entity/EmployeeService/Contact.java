package com.example.compositeservice.entity.EmployeeService;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Contact {

	Integer id;
	
	String firstName;
	
	String lastName;
	
	String cellPhone;
	
	String alternatePhone;
	
	String email;
	
	String relationship;
	
	String type;
}
