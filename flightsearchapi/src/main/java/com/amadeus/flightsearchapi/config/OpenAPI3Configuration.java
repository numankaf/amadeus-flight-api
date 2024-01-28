package com.amadeus.flightsearchapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition( info = @Info(title = "Flight Search Api",
        description = "Flight Search Api developed for Amadeus Travel to Future Program",
        version = "${application.version}"))
public class OpenAPI3Configuration {

}