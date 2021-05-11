package com.smart4aviation.service;

import com.smart4aviation.dto.FlightCargoRegistration;
import com.smart4aviation.dto.FlightRegistration;

public interface FlightCommandHandler {
    void save(FlightRegistration flight);
    void save(FlightCargoRegistration flight);

}
