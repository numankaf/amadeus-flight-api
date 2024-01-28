package com.amadeus.flightsearchapi.controller;

import com.amadeus.flightsearchapi.dto.airport.AirportCrudDto;
import com.amadeus.flightsearchapi.entity.Airport;
import com.amadeus.flightsearchapi.exception.HttpResponse;
import com.amadeus.flightsearchapi.exception.util.HttpResponseUtil;
import com.amadeus.flightsearchapi.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airport")
@RequiredArgsConstructor
public class AirportController {
    private final AirportService airportService;

    @PostMapping()
    public ResponseEntity<HttpResponse> create(@RequestBody AirportCrudDto dto){
        airportService.create(dto);
        return HttpResponseUtil.createHttpResponse(HttpStatus.OK, "Created a new Airport successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpResponse> update(@PathVariable Long id, @RequestBody AirportCrudDto dto){
        airportService.update(id,dto);
        return HttpResponseUtil.createHttpResponse(HttpStatus.OK, "Updated a new Airport successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponse> delete(@PathVariable Long id){
        airportService.delete(id);
        return HttpResponseUtil.createHttpResponse(HttpStatus.OK, "Deleted a new Airport successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> getById(@PathVariable Long id){
        return ResponseEntity.ok(airportService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Airport>> getAll(){
        return ResponseEntity.ok(airportService.getAll());
    }
    @GetMapping()
    public ResponseEntity<Page<Airport>> getAll(Pageable pageable){
        return ResponseEntity.ok(airportService.getAll(pageable));
    }


}
