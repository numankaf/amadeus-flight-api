package com.amadeus.flightsearchapi.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "FLIGHT")
public class Flight extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "departure_airport_id", nullable = false)
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id",  nullable = false)
    private Airport arrivalAirport;

    @Column(name = "DEPARTURE_DATE")
    private Date departureDate;

    @Column(name = "RETURN_DATE")
    private Date returnDate;

    @Column(name = "PRICE")
    private Double price;
}
