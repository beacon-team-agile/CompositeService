package com.example.compositeservice.entity.HousingService;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Facility {
    private Integer id;
    private Integer houseId;
    private String type;
    private String description;
    private Integer quantity;
}
