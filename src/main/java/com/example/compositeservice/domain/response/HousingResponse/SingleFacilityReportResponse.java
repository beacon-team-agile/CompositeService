package com.example.compositeservice.domain.response.HousingResponse;

import com.example.compositeservice.domain.response.common.ResponseStatus;
import com.example.compositeservice.entity.HousingService.FacilityReport;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SingleFacilityReportResponse {
    private ResponseStatus responseStatus;
    private FacilityReport facilityReport;
}
