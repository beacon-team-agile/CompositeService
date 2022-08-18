package com.example.compositeservice.domain.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DigitalDocumentUploadRequest {
	private MultipartFile multifile;
	private String title;
	private String description;
	private boolean is_required;

}
