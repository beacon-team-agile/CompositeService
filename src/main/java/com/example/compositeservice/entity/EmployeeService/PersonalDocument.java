package com.example.compositeservice.entity.EmployeeService;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class PersonalDocument {

	Integer id;
	
	String path;
	
	String title;
	
	String comment;

	String createDate;
}
