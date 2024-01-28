package com.amadeus.flightsearchapi.service;

import com.amadeus.flightsearchapi.dto.airport.AirportCrudDto;
import com.amadeus.flightsearchapi.entity.Airport;
import com.amadeus.flightsearchapi.exception.domain.AirportNotFoundException;
import com.amadeus.flightsearchapi.mapper.AirportMapper;
import com.amadeus.flightsearchapi.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    public void create(AirportCrudDto dto){
        Airport airport = airportMapper.toEntity(dto);
        airportRepository.save(airport);
    }

    public void update(Long id,AirportCrudDto dto){
        Airport airport = airportRepository.findById(id).orElseThrow(AirportNotFoundException::new);
        airportMapper.update(airport, dto);
        airportRepository.save(airport);
    }

    public void delete(Long id){
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


}
