package com.smart4aviation.dto.airport;

import lombok.Value;

@Value
public class AirportDetails {
    int numberOfDepartingFlights;
    int numberOfArrivingFlights;
    int totalNumberOfBaggageArriving;
    int totalNumberOfBaggageDeparting;
}
