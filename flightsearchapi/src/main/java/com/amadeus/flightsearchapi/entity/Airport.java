package com.amadeus.flightsearchapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "AIRPORT")
public class Airport extends BaseEntity{
    @Column(name = "CITY")
    private String city;

    @OneToMany(mappedBy = "departureAirport", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Flight> departureFlights;

    @OneToMany(mappedBy = "arrivalAirport",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Flight> arrivalFlights;

}
