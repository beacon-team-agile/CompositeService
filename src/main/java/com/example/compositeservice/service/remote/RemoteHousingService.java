package com.example.compositeservice.service.remote;

import com.example.compositeservice.domain.request.HousingService.FacilityReportDetailRequest;
import com.example.compositeservice.domain.request.HousingService.FacilityReportRequest;
import com.example.compositeservice.domain.response.HousingResponse.*;
import com.example.compositeservice.entity.HousingService.FacilityReport;
import com.example.compositeservice.entity.HousingService.FacilityReportDetail;
import com.example.compositeservice.entity.HousingService.House;
import com.example.compositeservice.entity.HousingService.Landlord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("housing-service")
public interface RemoteHousingService {

    @PostMapping("housing-service/housing/{landlordId}")
    SingleHouseResponse addHouse(@RequestBody House requestHouse);
    @GetMapping("housing-service/housing/{houseId}")
    SingleHouseResponse getHouseById(@PathVariable Integer houseId);

    @GetMapping("housing-service/housing/all")
    AllHousesResponse getAllHouses();

    @DeleteMapping("housing-service/housing/{houseId}")
    SingleHouseResponse deleteHouse(@PathVariable Integer houseId);

    @GetMapping("housing-service/housing/landlord/{houseId}")
    Landlord getLandlordByHouseId(@PathVariable Integer houseId);

    @PostMapping("housing-service/facility-report/add_report")
    SingleFacilityReportResponse addFacilityReport(@RequestBody FacilityReportRequest request);

    @GetMapping("housing-service/facility-report/{facilityReportId}")
    SingleFacilityReportResponse getFacilityReportById(@PathVariable Integer facilityReportId);

    @GetMapping("housing-service/facility-report/all")
    AllFacilityReportsResponse getAllFacilityReports();

    @GetMapping("housing-service/facility-report/facility/{facilityId}")
    AllFacilityReportsResponse getFacilityReportsByFacilityId(@PathVariable Integer facilityId);

    @PostMapping("housing-service/facility-report-detail/add")
    SingleFacilityReportDetailResponse addFacilityReportDetail(@RequestBody FacilityReportDetailRequest facilityReportDetailRequest);

    @GetMapping("housing-service/facility-report-detail/{facilityReportDetailId}")
    SingleFacilityReportDetailResponse getFacilityReportDetailById(@PathVariable Integer facilityReportDetailId);

    @GetMapping("housing-service/facility-report-detail/all")
    AllFacilityReportDetailsResponse getAllFacilityReportDetails();

    @GetMapping("housing-service/facility-report-detail/report/{facilityReportId}")
    AllFacilityReportDetailsResponse getFacilityReportDetailsByFacilityReportId(@PathVariable Integer facilityReportId);

    @PatchMapping("housing-service/facility-report-detail/{id}/{comment}")
    void updateFacilityReportDetail(@PathVariable Integer id,@PathVariable String comment);
}
