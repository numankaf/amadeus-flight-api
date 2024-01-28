package com.amadeus.flightsearchapi.dto.flight;

import lombok.Data;

import java.util.Date;

@Data
public class FlightSearchDto {
    private String departureAirport;

    private String arrivalAirport;

    private Date departureDate;

    private Date returnDate;
}
