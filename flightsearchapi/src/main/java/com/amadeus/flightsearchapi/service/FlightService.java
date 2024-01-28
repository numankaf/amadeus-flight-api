package com.amadeus.flightsearchapi.service;

import com.amadeus.flightsearchapi.dto.flight.FlightCrudDto;
import com.amadeus.flightsearchapi.dto.flight.FlightInfoDto;
import com.amadeus.flightsearchapi.dto.flight.FlightSearchDto;
import com.amadeus.flightsearchapi.dto.flight.SearchResultDto;
import com.amadeus.flightsearchapi.entity.Airport;
import com.amadeus.flightsearchapi.entity.Flight;
import com.amadeus.flightsearchapi.entity.QFlight;
import com.amadeus.flightsearchapi.exception.domain.AirportNotFoundException;
import com.amadeus.flightsearchapi.exception.domain.FlightNotFoundException;
import com.amadeus.flightsearchapi.mapper.FlightMapper;
import com.amadeus.flightsearchapi.repository.AirportRepository;
import com.amadeus.flightsearchapi.repository.FlightRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.dsl.Expressions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
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

    public List<SearchResultDto> search(FlightSearchDto searchDto){
        QFlight qFlight = QFlight.flight;
        BooleanBuilder booleanBuilder  = new BooleanBuilder();

        if(searchDto.getDepartureAirport() != null){
            booleanBuilder.and(qFlight.departureAirport.city.containsIgnoreCase(searchDto.getDepartureAirport()));
        }
        if(searchDto.getArrivalAirport() != null){
            booleanBuilder.and(qFlight.arrivalAirport.city.containsIgnoreCase(searchDto.getArrivalAirport()));
        }

        if (searchDto.getDepartureDate() !=null){
            booleanBuilder.and(Expressions.dateTimeOperation(Date.class, Ops.DateTimeOps.DATE,qFlight.departureDate).eq(searchDto.getDepartureDate()));
        }

        if (searchDto.getReturnDate() !=null){
            booleanBuilder.and(Expressions.dateTimeOperation(Date.class, Ops.DateTimeOps.DATE,qFlight.returnDate).eq(searchDto.getReturnDate()));
        }
        List<Flight> flights = (List<Flight>) flightRepository.findAll(booleanBuilder);
        return flights.stream().map(this::mapToFlightSearchDto).toList();
    }


    private SearchResultDto mapToFlightSearchDto(Flight flight){
        SearchResultDto searchResultDto = new SearchResultDto();
        Set<FlightInfoDto> flightInfoDtos = new HashSet<>();
        searchResultDto.setType("ONE-WAY FLIGHT");

        FlightInfoDto dto1 = new FlightInfoDto();
        dto1.setDepartureAirportCity(flight.getDepartureAirport().getCity());
        dto1.setArrivalAirportCity(flight.getArrivalAirport().getCity());
        dto1.setDepartureDate(flight.getDepartureDate());
        flightInfoDtos.add(dto1);

        if(flight.getReturnDate() !=null){
            searchResultDto.setType("TWO-WAY FLIGHT");
            FlightInfoDto dto2 = new FlightInfoDto();
            dto2.setDepartureAirportCity(flight.getArrivalAirport().getCity());
            dto2.setArrivalAirportCity(flight.getDepartureAirport().getCity());
            dto2.setDepartureDate(flight.getReturnDate());

            flightInfoDtos.add(dto2);
        }
        searchResultDto.setFlights(flightInfoDtos);
        return searchResultDto;
    }
}
