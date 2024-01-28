package com.amadeus.flightsearchapi.repository;

import com.amadeus.flightsearchapi.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;


public interface FlightRepository extends JpaRepository<Flight, Long>, QuerydslPredicateExecutor<Flight> {
    void deleteAllByArrivalAirport_Id(Long id);
    void deleteAllByDepartureAirport_Id(Long id);
}
