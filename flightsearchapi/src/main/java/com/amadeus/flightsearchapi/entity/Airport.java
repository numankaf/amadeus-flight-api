package com.amadeus.flightsearchapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "AIRPORT")
public class Airport extends BaseEntity{
    @Column(name = "CITY")
    private String city;
}
