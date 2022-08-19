package com.example.compositeservice.domain.response.ApplicationResponse;


import com.example.compositeservice.domain.response.common.ResponseStatus;
import com.example.compositeservice.entity.ApplicationService.ApplicationWorkFlow;
import com.example.compositeservice.entity.ApplicationService.DigitalDocument;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SingleDigitalDocumentResponse {
    private ResponseStatus responseStatus;

    private DigitalDocument digitalDocument;
}
