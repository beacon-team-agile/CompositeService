package com.example.compositeservice.domain.response.HousingService;

import com.example.compositeservice.domain.response.common.ResponseStatus;
import com.example.compositeservice.entity.HousingService.FacilityReportDetail;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllFacilityReportDetailsResponse {
    private ResponseStatus responseStatus;
    private List<FacilityReportDetail> facilityReportDetailList;
}
