package com.example.compositeservice.domain.response.HousingService;

import java.io.Serializable;

import com.example.compositeservice.domain.response.common.ResponseStatus;
import com.example.compositeservice.entity.HousingService.FacilityReportAbstract;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SingleFacilityReportResponse implements Serializable{
    private ResponseStatus responseStatus;
    private FacilityReportAbstract facilityReport;
}
