package com.example.compositeservice.service;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.rsa.crypto.RsaSecretEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.compositeservice.domain.request.DigitalDocumentUploadRequest;
import com.example.compositeservice.domain.request.PersonalDocumentUploadRequest;
import com.example.compositeservice.domain.response.ApplicationResponse.AddDigitalDocumentResponse;
import com.example.compositeservice.domain.response.common.ResponseStatus;
import com.example.compositeservice.entity.ApplicationService.DigitalDocument;
import com.example.compositeservice.entity.EmployeeService.PersonalDocument;
import com.example.compositeservice.service.remote.RemoteApplicationService;
import com.example.compositeservice.service.remote.RemoteEmployeeService;

@Service
public class CompositeFileService {
	
	private final String AES_KEY = "securedEndpoint123";
	private final String AES_SALT = "414243444546";
    private RemoteEmployeeService employeeService;
    private RemoteApplicationService applicationService;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setEmployeeService(RemoteEmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    @Autowired
    public void setApplicationService(RemoteApplicationService applicationService) {
        this.applicationService = applicationService;
    }
    
    public ResponseStatus uploadPersonalDocumentToUser(PersonalDocumentUploadRequest pdur) {
    	return employeeService.UploadDocumentToUser(
    			pdur.getMultifile()
    			, pdur.getUserid()
    			, pdur.getTitle()
    			, pdur.getComment());
    }
    
    public AddDigitalDocumentResponse uploadDigitalDocument(DigitalDocumentUploadRequest ddur) {
    	Integer dot = ddur.getMultifile().getOriginalFilename().lastIndexOf('.');
    	System.out.println(dot);
    	if(dot == 0) {return AddDigitalDocumentResponse.builder().responseStatus(ResponseStatus.builder().is_success(false).message("file type undefined!").build()).build();}
    	String inferredType = ddur.getMultifile().getOriginalFilename().substring(dot);
    	SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
    	String dateFormat = sdf.format(Calendar.getInstance().getTime());
    	String docName = "digitalDocument_" + dateFormat.replace(" ", "_") + "_" + ddur.getTitle().replace(" ", "_") + inferredType;
    	System.out.println(docName);
    	ResponseStatus rs = employeeService.UploadDocument(ddur.getMultifile(), docName);
    	if(rs.is_success()) {
    		return applicationService.addDigitalDocument(DigitalDocument.builder()
    				.path(docName)
    				.title(ddur.getTitle())
    				.description(ddur.getDescription())
    				.is_required(ddur.is_required())
    				.type(inferredType)
    				.build());
    	}else {
    		return AddDigitalDocumentResponse.builder().responseStatus(ResponseStatus.builder().is_success(false).message(rs.getMessage()).build()).build();
    	}
    }
    
    public ResponseEntity<ByteArrayResource> downloadDocument(String docKey) {
    	return employeeService.downloadFile(docKey);
    }
    
    public ResponseEntity<ByteArrayResource> downloadDocumentEncryptedKey(String docKey) {
    	TextEncryptor textEnc = Encryptors.text(AES_KEY, AES_SALT);
    	String actualKey = textEnc.decrypt(docKey);
    	return employeeService.downloadFile(actualKey);
    }
    
    
}
