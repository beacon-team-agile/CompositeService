package com.example.compositeservice.domain.response.HousingResponse;
import com.example.compositeservice.domain.response.common.ResponseStatus;
import com.example.compositeservice.entity.HousingService.House;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SingleHouseResponse {
    private ResponseStatus responseStatus;
    private House house;
}
