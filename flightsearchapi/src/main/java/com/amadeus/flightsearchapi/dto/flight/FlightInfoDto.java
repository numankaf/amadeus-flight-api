package com.amadeus.flightsearchapi.dto.flight;

import lombok.Data;

import java.util.Date;
@Data
public class FlightInfoDto {
    private String departureAirportCity;
    private String arrivalAirportCity;
    private Date departureDate;
}
