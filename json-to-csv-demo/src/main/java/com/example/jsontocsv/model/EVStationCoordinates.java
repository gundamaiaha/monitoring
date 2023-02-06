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
public class EVStationCoordinates {

    @JsonProperty(value = "alt",defaultValue = "")
    private String altitude;

    @JsonProperty(value = "lon",defaultValue = "")
    private String longitude;

    @JsonProperty(value = "type",defaultValue = "")
    private String coordinateType;

    @JsonProperty(value = "lat",defaultValue = "")
    private String latitude;


}
