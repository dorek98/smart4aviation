package com.smart4aviation.service;

import com.smart4aviation.dto.cargo.FlightCargoRegistration;
import com.smart4aviation.dto.flight.FlightRegistration;

public interface FlightCommandHandler {
    void save(FlightRegistration flight);
    void save(FlightCargoRegistration flight);

}
