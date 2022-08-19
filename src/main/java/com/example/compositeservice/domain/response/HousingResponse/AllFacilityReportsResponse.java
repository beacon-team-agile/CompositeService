package com.example.compositeservice.domain.response.HousingResponse;

import com.example.compositeservice.domain.response.common.ResponseStatus;
import com.example.compositeservice.entity.HousingService.FacilityReportAbstract;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllFacilityReportsResponse {
    private ResponseStatus responseStatus;
    private List<FacilityReportAbstract> facilityReportList;
}
