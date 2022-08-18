package com.example.compositeservice.entity.EmployeeService;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class VisaStatus {

	Integer id;
	
	String visaType;
	
	Boolean activeFlag;

	String startDate;

	String endDate;

	String lastModificationDate;
}
