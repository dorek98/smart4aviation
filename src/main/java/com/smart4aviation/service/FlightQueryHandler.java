package com.smart4aviation.service;

import com.smart4aviation.dto.airport.AirportDetails;
import com.smart4aviation.dto.flight.FlightDetails;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;

public interface FlightQueryHandler {

    ResponseEntity<FlightDetails> getFlight(int flightNumber, OffsetDateTime date);

    ResponseEntity<AirportDetails> getAirport(String airportIATACode, OffsetDateTime date);
}
