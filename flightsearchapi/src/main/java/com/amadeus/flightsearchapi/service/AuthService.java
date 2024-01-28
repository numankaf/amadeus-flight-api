package com.amadeus.flightsearchapi.service;

import com.amadeus.flightsearchapi.config.JwtTokenProvider;
import com.amadeus.flightsearchapi.config.UserPrincipal;
import com.amadeus.flightsearchapi.dto.auth.AuthorizationRequestDto;
import com.amadeus.flightsearchapi.dto.auth.AuthorizationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @Value("${application.jwt_expires_in}")
    private long jwtExpireDuration;

    public AuthorizationResponseDto authorize(AuthorizationRequestDto dto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        Authentication auth = authenticationManager.authenticate(token);
        UserPrincipal user = (UserPrincipal) auth.getPrincipal();
        String accessToken = tokenProvider.generateToken(user);
        return AuthorizationResponseDto.builder()
                .accessToken(accessToken)
                .tokenType("Bearer")
                .expiresIn(jwtExpireDuration)
                .build();
    }

}
