package com.smart4aviation.dto.flight;

import lombok.Value;

import java.time.OffsetDateTime;

@Value
public class FlightRegistration {

    long flightId;
    int flightNumber;
    String departureAirportIATACode;
    String arrivalAirportIATACode;
    OffsetDateTime departureDate;
}
