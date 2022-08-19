package com.example.compositeservice.entity.HousingService;

import java.util.ArrayList;
import java.util.List;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class House {
    private Integer Id;

    private Landlord landlord;

    private String address;

    private Integer maxOccupant;

    private List<Facility> facilityList = new ArrayList<>();
}
