package com.example.compositeservice.domain.response.EmployeeResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FilePathResponse {
	String path;
	
	String title;
	
	String comment;
}
