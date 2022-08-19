package com.example.compositeservice.domain.response.HousingResponse;

import com.example.compositeservice.domain.response.common.ResponseStatus;
import com.example.compositeservice.entity.HousingService.Facility;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllFacilitiesResponse {
    private ResponseStatus responseStatus;
    private List<Facility> facilityList;
}
