package com.example.jsontocsv.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EVStationCInfo {

    @JsonProperty("bType")
    private String billingType;

    @JsonProperty("cBID")
    private String chargerBID;

    @JsonProperty("cType")
    private String chargerType;

    @JsonProperty("cID")
    private String chargerID;

    @JsonProperty("cSpdType")
    private String chargerSpeedType;




}
