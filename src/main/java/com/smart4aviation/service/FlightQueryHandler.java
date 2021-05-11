package com.smart4aviation.service;

import com.smart4aviation.dto.AirportDetails;
import com.smart4aviation.dto.FlightDetails;
import org.springframework.http.ResponseEntity;

public interface FlightQueryHandler {

    ResponseEntity<FlightDetails> getFlight(int flightNumber, String date);

    ResponseEntity<AirportDetails> getAirport(String airportIATACode, String date);
}
