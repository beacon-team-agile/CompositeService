package com.example.compositeservice.entity.HousingService;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class House {
    private Integer id;
    private Integer landlordId;
    private String address;
    private Integer maxOccupant;
}
