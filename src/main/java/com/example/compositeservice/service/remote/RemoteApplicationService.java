package com.example.compositeservice.service.remote;

import com.example.compositeservice.domain.request.ApplicationService.EmailApplicationStatusRequest;
import com.example.compositeservice.domain.response.ApplicationResponse.SingleApplicationWorkFlowResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("application-service")
public interface RemoteApplicationService {
    @GetMapping("application-service/applicationWorkFlow/email_result/{id}")
    SingleApplicationWorkFlowResponse emailApplicationResultById(@PathVariable Integer id,
                                                      @RequestBody EmailApplicationStatusRequest emailApplicationStatusRequest);
}
