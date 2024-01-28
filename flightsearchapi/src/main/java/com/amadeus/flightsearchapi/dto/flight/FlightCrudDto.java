package com.amadeus.flightsearchapi.dto.flight;

import lombok.Data;


import java.util.Date;

@Data
public class FlightCrudDto {
    private Long departureAirportId;

    private Long arrivalAirportId;

    private Date departureDate;

    private Date returnDate;

    private Double price;
}
