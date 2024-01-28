package com.amadeus.flightsearchapi.service;

import com.amadeus.flightsearchapi.dto.flight.FlightCrudDto;
import com.amadeus.flightsearchapi.entity.Airport;
import com.amadeus.flightsearchapi.entity.Flight;
import com.amadeus.flightsearchapi.exception.domain.AirportNotFoundException;
import com.amadeus.flightsearchapi.exception.domain.FlightNotFoundException;
import com.amadeus.flightsearchapi.mapper.FlightMapper;
import com.amadeus.flightsearchapi.repository.AirportRepository;
import com.amadeus.flightsearchapi.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final AirportRepository airportRepository;


    private void setAirportProperties(Flight flight, FlightCrudDto dto){
        Airport departureAirport = airportRepository.findById(dto.getDepartureAirportId()).orElseThrow(AirportNotFoundException::new);
        Airport arrivalAirport = airportRepository.findById(dto.getArrivalAirportId()).orElseThrow(AirportNotFoundException::new);
        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);
    }
    public void create(FlightCrudDto dto){
        Flight flight = flightMapper.toEntity(dto);
        setAirportProperties(flight, dto);

        flightRepository.save(flight);
    }

    public void update(Long id,FlightCrudDto dto){
        Flight flight = flightRepository.findById(id).orElseThrow(FlightNotFoundException::new);
        flightMapper.update(flight, dto);
        setAirportProperties(flight, dto);

        flightRepository.save(flight);
    }

    public void delete(Long id){
        flightRepository.deleteById(id);
    }

    public Flight getById(Long id){
        return flightRepository.findById(id).orElseThrow(FlightNotFoundException::new);
    }

    public List<Flight> getAll(){
        return flightRepository.findAll();
    }

    public Page<Flight> getAll(Pageable pageable){
        return flightRepository.findAll(pageable);
    }
}
