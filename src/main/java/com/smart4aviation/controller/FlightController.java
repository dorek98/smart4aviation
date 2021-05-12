package com.smart4aviation.controller;

import com.smart4aviation.dto.airport.AirportDetails;
import com.smart4aviation.dto.cargo.FlightCargoRegistration;
import com.smart4aviation.dto.flight.FlightDetails;
import com.smart4aviation.dto.flight.FlightRegistration;
import com.smart4aviation.service.FlightCommandHandlerImpl;
import com.smart4aviation.service.FlightQueryHandlerImpl;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/task")
@AllArgsConstructor
public class FlightController {

    private final FlightQueryHandlerImpl queryHandler;
    private final FlightCommandHandlerImpl commandHandler;

    @GetMapping("/flights")
    public ResponseEntity<FlightDetails> getFlightDetails(@RequestParam("flightNumber") int flightNumber,
                                                          @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime date) {
        return queryHandler.getFlight(flightNumber, date);
    }

    @GetMapping("/airports")
    @ResponseBody
    public ResponseEntity<AirportDetails> getAirportDetails(@RequestParam("airportIATACode") String airportIATACode,
                                                            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime date) {
        return queryHandler.getAirport(airportIATACode, date);
    }

    @PostMapping("/flight")
    public ResponseEntity<HttpStatus> createFlight(final @RequestBody List<FlightRegistration> flightRegistrationList) {
        flightRegistrationList.forEach(commandHandler::save);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/cargo")
    public ResponseEntity<HttpStatus> createCargo(final @RequestBody List<FlightCargoRegistration> flightCargoRegistrationList) {
        flightCargoRegistrationList.forEach(commandHandler::save);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
