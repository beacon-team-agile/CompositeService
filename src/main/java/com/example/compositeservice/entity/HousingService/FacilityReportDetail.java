package com.example.compositeservice.entity.HousingService;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FacilityReportDetail {
    private Integer id;
    private Integer facilityReportId;
    private String employeeId;
    private String comment;
    private Date createDate;
    private Date lastModificationDate;
}
