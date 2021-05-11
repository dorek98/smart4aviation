package com.smart4aviation.service;

import com.smart4aviation.dto.AirportDetails;
import com.smart4aviation.dto.FlightDetails;
import com.smart4aviation.model.Baggage;
import com.smart4aviation.model.Cargo;
import com.smart4aviation.model.Flight;
import com.smart4aviation.repository.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class FlightQueryHandlerImpl {
    private final FlightRepository flightRepository;

    public ResponseEntity<FlightDetails> getFlight(int flightNumber, String date) {
        List<Flight> flightList = flightRepository.findAll().stream()
                .filter(f -> f.getFlightNumber() == flightNumber && f.getDepartureDate().equals(date))
                .collect(Collectors.toList());

        if (flightList.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        Flight flight = flightList.get(0);
        int baggageWeight = flight.getBaggages().stream()
                .mapToInt(Baggage::getWeight)
                .sum();

        int cargoWeight = flight.getCargos().stream()
                .mapToInt(Cargo::getWeight)
                .sum();

        int totalWeight = baggageWeight + cargoWeight;

        return ResponseEntity.ok(new FlightDetails(flightNumber, cargoWeight, baggageWeight, totalWeight));
    }

    public ResponseEntity<AirportDetails> getAirport(String airportIATACode, String date) {
        List<Flight> flightList = flightRepository.findAll();

        int totalNumberOfBaggageArriving = 0;
        int totalNumberOfBaggageDeparting = 0;

        List<Flight> departingFlights = flightList.stream()
                .filter(f -> f.getDepartureAirportIATACode().equals(airportIATACode) && f.getDepartureDate().equals(date))
                .collect(Collectors.toList());

        List<Flight> arrivingFlights = flightList.stream()
                .filter(f -> f.getArrivalAirportIATACode().equals(airportIATACode) && f.getDepartureDate().equals(date))
                .collect(Collectors.toList());

        int numberOfDepartingFlights = departingFlights.size();
        int numberOfArrivingFlights = arrivingFlights.size();

        if (arrivingFlights.size() != 0) {
            for (Flight f : arrivingFlights) {
                totalNumberOfBaggageArriving += f.getBaggages().stream()
                        .mapToInt(Baggage::getPieces)
                        .sum();
            }
        }

        if (departingFlights.size() != 0) {
            for (Flight f : departingFlights) {
                totalNumberOfBaggageDeparting += f.getBaggages().stream()
                        .mapToInt(Baggage::getPieces)
                        .sum();
            }
        }
        return ResponseEntity.ok(new AirportDetails(numberOfDepartingFlights, numberOfArrivingFlights, totalNumberOfBaggageArriving, totalNumberOfBaggageDeparting));
    }

}
