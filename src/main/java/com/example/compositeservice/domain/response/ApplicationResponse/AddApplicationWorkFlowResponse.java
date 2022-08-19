package com.example.compositeservice.domain.response.ApplicationResponse;

import com.example.compositeservice.domain.response.common.ResponseStatus;
import com.example.compositeservice.entity.ApplicationService.ApplicationWorkFlow;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddApplicationWorkFlowResponse {
    private ResponseStatus responseStatus;
    private Integer id;
    private ApplicationWorkFlow applicationWorkFlow;
}
