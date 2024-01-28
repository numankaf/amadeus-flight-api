package com.amadeus.flightsearchapi.dto.flight;

import lombok.Data;

import java.util.Set;

@Data
public class SearchResultDto {
    private String type;
    private Set<FlightInfoDto> flights;

}
