package com.amadeus.flightsearchapi.service;

import com.amadeus.flightsearchapi.dto.airport.AirportCrudDto;
import com.amadeus.flightsearchapi.entity.Airport;
import com.amadeus.flightsearchapi.entity.Flight;
import com.amadeus.flightsearchapi.exception.domain.AirportNotFoundException;
import com.amadeus.flightsearchapi.mapper.AirportMapper;
import com.amadeus.flightsearchapi.repository.AirportRepository;
import com.amadeus.flightsearchapi.repository.FlightRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AirportService {
    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;
    private final FlightRepository flightRepository;

    public void create(AirportCrudDto dto){
        Airport airport = airportMapper.toEntity(dto);
        airportRepository.save(airport);
    }

    public void update(Long id,AirportCrudDto dto){
        Airport airport = airportRepository.findById(id).orElseThrow(AirportNotFoundException::new);
        airportMapper.update(airport, dto);
        airportRepository.save(airport);
    }

    @Transactional
    public void delete(Long id){
        flightRepository.deleteAllByArrivalAirport_Id(id);
        flightRepository.deleteAllByDepartureAirport_Id(id);
        airportRepository.deleteById(id);
    }

    public Airport getById(Long id){
        return airportRepository.findById(id).orElseThrow(AirportNotFoundException::new);
    }

    public List<Airport> getAll(){
        return airportRepository.findAll();
    }

    public Page<Airport> getAll(Pageable pageable){
        return airportRepository.findAll(pageable);
    }

    public Page<Airport> search(AirportCrudDto dto, Pageable pageable){
        Airport airport = new Airport();
        airport.setCity(dto.getCity());

        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Airport> example = Example.of(airport,matcher);
        return airportRepository.findAll(example,pageable);
    }


}
