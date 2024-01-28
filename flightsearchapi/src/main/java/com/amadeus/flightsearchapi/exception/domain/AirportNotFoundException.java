package com.amadeus.flightsearchapi.exception.domain;

public class AirportNotFoundException extends RuntimeException{
    public AirportNotFoundException(){
    }
    public AirportNotFoundException(String message){
        super(message);
    }
}
