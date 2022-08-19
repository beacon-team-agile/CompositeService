package com.example.compositeservice.domain.response.ApplicationResponse;

import com.example.compositeservice.domain.response.common.ResponseStatus;
import com.example.compositeservice.entity.ApplicationService.ApplicationWorkFlow;
import com.example.compositeservice.entity.ApplicationService.DigitalDocument;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class MultipleDigitalDocumentResponse {
    private ResponseStatus responseStatus;
    private List<DigitalDocument> digitalDocumentList;
}
