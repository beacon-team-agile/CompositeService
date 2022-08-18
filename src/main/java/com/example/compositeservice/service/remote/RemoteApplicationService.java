package com.example.compositeservice.service.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.compositeservice.domain.response.ApplicationResponse.MultipleApplicationWorkFlowResponse;


@FeignClient("application-service")
public interface RemoteApplicationService {

    @GetMapping("application-service/applicationWorkFlow/all")
    public MultipleApplicationWorkFlowResponse getAllApplicationWorkFlow();
}
