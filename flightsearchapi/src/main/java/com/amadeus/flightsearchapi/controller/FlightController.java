package com.amadeus.flightsearchapi.controller;

import com.amadeus.flightsearchapi.dto.flight.FlightCrudDto;
import com.amadeus.flightsearchapi.dto.flight.FlightSearchDto;
import com.amadeus.flightsearchapi.dto.flight.SearchResultDto;
import com.amadeus.flightsearchapi.entity.Flight;
import com.amadeus.flightsearchapi.exception.HttpResponse;
import com.amadeus.flightsearchapi.exception.util.HttpResponseUtil;
import com.amadeus.flightsearchapi.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping()
    public ResponseEntity<HttpResponse> create(@RequestBody FlightCrudDto dto){
        flightService.create(dto);
        return HttpResponseUtil.createHttpResponse(HttpStatus.OK, "Created a new Flight successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpResponse> update(@PathVariable Long id, @RequestBody FlightCrudDto dto){
        flightService.update(id,dto);
        return HttpResponseUtil.createHttpResponse(HttpStatus.OK, "Updated a new Flight successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponse> delete(@PathVariable Long id){
        flightService.delete(id);
        return HttpResponseUtil.createHttpResponse(HttpStatus.OK, "Deleted a new Flight successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getById(@PathVariable Long id){
        return ResponseEntity.ok(flightService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Flight>> getAll(){
        return ResponseEntity.ok(flightService.getAll());
    }
    @GetMapping()
    public ResponseEntity<Page<Flight>> getAll(Pageable pageable){
        return ResponseEntity.ok(flightService.getAll(pageable));
    }

    @PostMapping("/search")
    public ResponseEntity<List<SearchResultDto>> search(@RequestBody FlightSearchDto dto){
        return ResponseEntity.ok(flightService.search(dto));
    }
}
