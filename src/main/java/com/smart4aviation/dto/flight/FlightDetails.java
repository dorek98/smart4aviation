package com.smart4aviation.dto.flight;

import lombok.Value;

@Value
public class FlightDetails {

    int flightNumber;
    int cargoWeightInKg;
    int baggageWeightInKg;
    int totalWeightInKg;
    int cargoWeightInLb;
    int baggageWeightInLb;
    int totalWeightInLb;
}
