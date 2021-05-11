package com.smart4aviation.controller;

import com.smart4aviation.dto.AirportDetails;
import com.smart4aviation.dto.FlightCargoRegistration;
import com.smart4aviation.dto.FlightDetails;
import com.smart4aviation.dto.FlightRegistration;
import com.smart4aviation.service.FlightCommandHandlerImpl;
import com.smart4aviation.service.FlightQueryHandlerImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@AllArgsConstructor
public class FlightController {

    private final FlightQueryHandlerImpl queryHandler;
    private final FlightCommandHandlerImpl commandHandler;

    @GetMapping("/flights/{flightNumber}/{date}")
    public ResponseEntity<FlightDetails> getFlightDetails(@PathVariable("flightNumber") int flightNumber, @PathVariable("date") String date) {
        return queryHandler.getFlight(flightNumber, date);
    }

    @GetMapping("/airports/{airportIATACode}/{date}")
    public ResponseEntity<AirportDetails> getAirportDetails(@PathVariable("airportIATACode") String airportIATACode, @PathVariable("date") String date) {
        return queryHandler.getAirport(airportIATACode, date);
    }

    @PostMapping("/flight")
    @ResponseBody
    public ResponseEntity<HttpStatus> createFlight(final @RequestBody List<FlightRegistration> flightRegistrationList) {
        flightRegistrationList.forEach(commandHandler::save);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/cargo")
    @ResponseBody
    public ResponseEntity<HttpStatus> createCargo(final @RequestBody List<FlightCargoRegistration> flightCargoRegistrationList) {
        flightCargoRegistrationList.forEach(commandHandler::save);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
