package com.amadeus.flightsearchapi.controller;


import com.amadeus.flightsearchapi.dto.flight.FlightCrudDto;
import com.amadeus.flightsearchapi.entity.Airport;
import com.amadeus.flightsearchapi.entity.Flight;
import com.amadeus.flightsearchapi.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/mock-api")
@RequiredArgsConstructor
public class MockApiController {
    private final AirportRepository airportRepository;
    @GetMapping()
    public ResponseEntity<List<FlightCrudDto>> getRandomData(){
        //random data with amount of 5-20
        int randomNum = ThreadLocalRandom.current().nextInt(5, 20);
        long numOfAirports = airportRepository.count();
        List<FlightCrudDto> dtos = new ArrayList<>();
         for (int i =0; i<randomNum; i++){

            FlightCrudDto flight = new FlightCrudDto();
            long departureAirportId  =ThreadLocalRandom.current().nextInt(1, (int) numOfAirports);
            long arrivalAirportId  = ThreadLocalRandom.current().nextInt(1, (int) numOfAirports);
            while (departureAirportId == arrivalAirportId){
                arrivalAirportId= ThreadLocalRandom.current().nextInt(1, (int) numOfAirports);
            }

            flight.setDepartureAirportId(departureAirportId);
            flight.setArrivalAirportId(arrivalAirportId);

            flight.setDepartureDate(new Date(new Date().getTime() - ThreadLocalRandom.current().nextInt(0,360000)));
            flight.setReturnDate(new Date(new Date().getTime() + ThreadLocalRandom.current().nextInt(0,360000)));

            flight.setPrice((double) ThreadLocalRandom.current().nextInt(5, 300));
            dtos.add(flight);
        }
         return ResponseEntity.ok(dtos);
    }
}
