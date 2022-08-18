package com.example.compositeservice.service.remote;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.example.compositeservice.domain.request.ApplicationService.EmailApplicationStatusRequest;
import com.example.compositeservice.domain.response.ApplicationResponse.SingleApplicationWorkFlowResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.compositeservice.domain.response.ApplicationResponse.AddDigitalDocumentResponse;
import com.example.compositeservice.domain.response.ApplicationResponse.MultipleApplicationWorkFlowResponse;
import com.example.compositeservice.domain.response.ApplicationResponse.SingleDigitalDocumentResponse;
import com.example.compositeservice.entity.ApplicationService.DigitalDocument;


@FeignClient("application-service")
public interface RemoteApplicationService {

    @GetMapping("application-service/applicationWorkFlow/all")
    public MultipleApplicationWorkFlowResponse getAllApplicationWorkFlow();
    
    @PostMapping("application-service/digitalDocument/add")
    public AddDigitalDocumentResponse addDigitalDocument(@RequestBody DigitalDocument digitalDocument);
    
    @GetMapping("application-service/digitalDocument/{id}")
    public SingleDigitalDocumentResponse getDigitalDocumentById(@PathVariable Integer id);
    
    @GetMapping("application-service/applicationWorkFlow/email_result/{id}")
    SingleApplicationWorkFlowResponse emailApplicationResultById(@PathVariable Integer id,
                                                      @RequestBody EmailApplicationStatusRequest emailApplicationStatusRequest);


}
