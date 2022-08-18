package com.example.compositeservice.domain.request;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PersonalDocumentUploadRequest {
	private MultipartFile multifile;
	private String userid;
	private String title;
	private String comment;
}
