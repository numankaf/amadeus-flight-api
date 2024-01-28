package com.amadeus.flightsearchapi.controller;

import com.amadeus.flightsearchapi.dto.auth.AuthorizationRequestDto;
import com.amadeus.flightsearchapi.dto.auth.AuthorizationResponseDto;
import com.amadeus.flightsearchapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    ResponseEntity<AuthorizationResponseDto> login(@RequestBody AuthorizationRequestDto dto){
        return ResponseEntity.ok(authService.authorize(dto));
    }
}
