package com.smart4aviation.dto;

import lombok.Value;

@Value
public class FlightRegistration {

    long flightId;
    int flightNumber;
    String departureAirportIATACode;
    String arrivalAirportIATACode;
    String departureDate;
}
