package com.smart4aviation.dto;

import lombok.Value;

import java.util.List;

@Value
public class FlightCargoRegistration {
    long flightId;
    List<BaggageRegistration> baggage;
    List<CargoRegistration> cargo;
}
