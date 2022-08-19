package com.example.compositeservice.domain.response.HousingService;

import com.example.compositeservice.domain.response.common.ResponseStatus;
import com.example.compositeservice.entity.HousingService.House;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllHousesResponse {
    private ResponseStatus responseStatus;
    private List<House> houseList;
}
