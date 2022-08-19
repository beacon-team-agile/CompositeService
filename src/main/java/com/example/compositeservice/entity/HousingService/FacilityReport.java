package com.example.compositeservice.entity.HousingService;
import java.util.Date;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FacilityReport {
    private Integer id;
    private String facilityId;
    private String employeeId;
    private String title;
    private String description;
    private Date createDate;
    private String status;
}
