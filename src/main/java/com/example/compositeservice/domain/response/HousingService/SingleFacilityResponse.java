package com.example.compositeservice.domain.response.HousingService;

import com.example.compositeservice.domain.response.common.ResponseStatus;
import com.example.compositeservice.entity.HousingService.Facility;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SingleFacilityResponse {
    private ResponseStatus responseStatus;
    private Facility facility;
}
