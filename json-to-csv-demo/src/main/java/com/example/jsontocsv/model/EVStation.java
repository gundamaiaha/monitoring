package com.example.jsontocsv.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EVStation {

    @JsonProperty("sID")
    private String chargeStationId;

    @JsonProperty("sName")
    private String stationName;


    @JsonProperty("sAddr")
    private String stationAddress;


    @JsonProperty("oTime")
    private String oTime;


    @JsonProperty("sPhone")
    private String stationPhone;

    @JsonProperty("sCrd")
    private EVStationCoordinates stationCoordinates;

    @JsonProperty("sRtsCrd")
    private EVStationCoordinates rtsCoordinates;


    @JsonProperty("cInfos")
    private List<EVStationCInfo> evStationInfos;


    @JsonProperty("sStatus")
    private String stationStatus;

    @JsonProperty("pId5")
    private String poiId;

    @JsonProperty("etc")
    private String extraInfo;


}
