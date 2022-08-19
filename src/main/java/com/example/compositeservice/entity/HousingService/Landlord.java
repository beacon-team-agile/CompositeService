package com.example.compositeservice.entity.HousingService;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Landlord {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String cellPhone;

}
