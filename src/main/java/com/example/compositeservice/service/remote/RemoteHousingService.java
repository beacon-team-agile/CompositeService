package com.example.compositeservice.service.remote;

import com.example.compositeservice.domain.response.HousingResponse.AllHousesResponse;
import com.example.compositeservice.domain.response.HousingResponse.SingleHouseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("housing-service")
public interface RemoteHousingService {

    @GetMapping("housing-service/housing/{id}")
    SingleHouseResponse getHouseById(@PathVariable Integer id);

    @GetMapping("housing-service/housing/all")
    AllHousesResponse getAllHouses();


}
