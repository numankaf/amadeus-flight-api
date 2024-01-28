package com.amadeus.flightsearchapi.exception.domain;

public class FlightNotFoundException extends RuntimeException{
    public FlightNotFoundException(){
    }
    public FlightNotFoundException(String message){
        super(message);
    }
}
