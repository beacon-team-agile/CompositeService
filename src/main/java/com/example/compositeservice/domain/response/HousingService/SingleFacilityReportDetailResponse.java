package com.example.compositeservice.domain.response.HousingService;

import com.example.compositeservice.domain.response.common.ResponseStatus;
import com.example.compositeservice.entity.HousingService.FacilityReportDetail;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SingleFacilityReportDetailResponse {
    private ResponseStatus responseStatus;
    private FacilityReportDetail facilityReportDetail;
}
