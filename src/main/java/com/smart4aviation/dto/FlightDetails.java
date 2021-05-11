package com.smart4aviation.dto;

import lombok.Value;

@Value
public class FlightDetails {

    int flightNumber;
    int cargoWeight;
    int baggageWeight;
    int totalWeight;
}
