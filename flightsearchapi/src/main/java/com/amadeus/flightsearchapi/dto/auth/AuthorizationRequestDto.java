package com.amadeus.flightsearchapi.dto.auth;

import lombok.Data;

@Data
public class AuthorizationRequestDto {
    private String username;
    private String password;
}
