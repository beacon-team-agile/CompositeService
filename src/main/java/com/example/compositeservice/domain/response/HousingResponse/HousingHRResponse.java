package com.example.compositeservice.domain.response.HousingResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import com.example.compositeservice.domain.response.common.ResponseStatus;

import java.util.List;

@Getter
@Setter
@Builder
public class HousingHRResponse {
    private ResponseStatus responseStatus;
    private String address;
    private String landlord;
    private String email;
    private String cellPhone;
    private Integer occupantNumber;
}
