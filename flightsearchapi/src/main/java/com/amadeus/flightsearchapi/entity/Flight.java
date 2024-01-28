package com.amadeus.flightsearchapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "AIRPORT")
public class Flight extends BaseEntity{
    @Column(name = "DEPARTURE_DATE")
    Date departureDate;

    @Column(name = "RETURN_DATE")
    Date returnDate;

    @Column(name = "PRICE")
    private Double price;
}
