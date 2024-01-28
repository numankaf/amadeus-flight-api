package com.amadeus.flightsearchapi.exception;

import com.amadeus.flightsearchapi.exception.domain.AirportNotFoundException;
import com.amadeus.flightsearchapi.exception.domain.FlightNotFoundException;
import com.amadeus.flightsearchapi.exception.util.HttpResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.amadeus.flightsearchapi.exception.constant.ExceptionConstant.*;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpResponse> handleBadCredentialsException(BadCredentialsException exception) {
        return HttpResponseUtil.createHttpErrorResponse(HttpStatus.UNAUTHORIZED, exception.getMessage(), exception);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<HttpResponse> handleAuthenticationException(AuthenticationException exception) {
        return HttpResponseUtil.createHttpErrorResponse(HttpStatus.UNAUTHORIZED, INVALID_CREDENTIALS, exception);
    }

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<HttpResponse> handleFlightNotFoundException(FlightNotFoundException exception) {
        return HttpResponseUtil.createHttpErrorResponse(HttpStatus.NOT_FOUND, FLIGHT_NOT_FOUND, exception);
    }
    @ExceptionHandler(AirportNotFoundException.class)
    public ResponseEntity<HttpResponse> handleAirportNotFoundException(AirportNotFoundException exception) {
        return HttpResponseUtil.createHttpErrorResponse(HttpStatus.NOT_FOUND, AIRPORT_NOT_FOUND, exception);
    }





}
