package com.amadeus.flightsearchapi.service;

import com.amadeus.flightsearchapi.dto.flight.FlightCrudDto;
import com.amadeus.flightsearchapi.entity.Flight;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduledJobService {
    private final RestTemplate restTemplate;
    private final FlightService flightService;
    @Value("${mock-api.url}")
    private String mockApi;

    @Scheduled(initialDelay=0, fixedRate=60*60*1000)
    @Transactional
    public void getFlights(){
        log.info("Executing scheduled job: getting flights...");
        ResponseEntity<List<FlightCrudDto>> response = restTemplate.exchange(
                mockApi,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FlightCrudDto>>() {
                });

        List<FlightCrudDto> flights = response.getBody();

        if (flights ==null){
            log.info("flights are null");
            return;
        }

        for (FlightCrudDto flightCrudDto:flights){
            flightService.create(flightCrudDto);
        }

    }
}
