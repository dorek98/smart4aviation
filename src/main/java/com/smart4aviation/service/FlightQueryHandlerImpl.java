package com.smart4aviation.service;

import com.smart4aviation.dto.airport.AirportDetails;
import com.smart4aviation.dto.flight.FlightDetails;
import com.smart4aviation.model.Baggage;
import com.smart4aviation.model.Cargo;
import com.smart4aviation.model.Flight;
import com.smart4aviation.model.WeightUnit;
import com.smart4aviation.repository.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class FlightQueryHandlerImpl implements FlightQueryHandler {
    private final FlightRepository flightRepository;

    @Override
    public ResponseEntity<FlightDetails> getFlight(int flightNumber, OffsetDateTime date) {
        final double LBTOKG = 0.45;
        final double KGTOLB = 2.2;
        try {
            Flight flight = flightRepository.findByFlightNumberAndDepartureDate(flightNumber, date)
                    .orElseThrow(EntityNotFoundException::new);

            int baggageWeightInKg = flight.getBaggages().stream()
                    .filter(baggage -> baggage.getWeightUnit() == WeightUnit.KG)
                    .mapToInt(Baggage::getWeight)
                    .sum();

            baggageWeightInKg += flight.getBaggages().stream()
                    .filter(baggage -> baggage.getWeightUnit() == WeightUnit.LB)
                    .mapToInt(Baggage::getWeight)
                    .sum() * LBTOKG;

            int cargoWeightInKg = flight.getCargos().stream()
                    .filter(cargo -> cargo.getWeightUnit() == WeightUnit.KG)
                    .mapToInt(Cargo::getWeight)
                    .sum();

            cargoWeightInKg += flight.getCargos().stream()
                    .filter(cargo -> cargo.getWeightUnit() == WeightUnit.LB)
                    .mapToInt(Cargo::getWeight)
                    .sum() * LBTOKG;

            int baggageWeightInLb = (int) (baggageWeightInKg * KGTOLB);
            int cargoWeightInLb = (int) (cargoWeightInKg * KGTOLB);

            int totalWeightInKg = baggageWeightInKg + cargoWeightInKg;
            int totalWeightInLb = baggageWeightInLb + cargoWeightInLb;
            FlightDetails flightDetails = new FlightDetails(flightNumber, cargoWeightInKg, baggageWeightInKg,
                    totalWeightInKg, cargoWeightInLb, baggageWeightInLb, totalWeightInLb);
            return ResponseEntity.ok(flightDetails);
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<AirportDetails> getAirport(String airportIATACode, OffsetDateTime date) {

        int totalNumberOfBaggageArriving = 0;
        int totalNumberOfBaggageDeparting = 0;
        List<Flight> departingFlights = flightRepository.findByDepartureAirportIATACodeAndDepartureDate(airportIATACode, date);
        List<Flight> arrivingFlights = flightRepository.findByArrivalAirportIATACodeAndDepartureDate(airportIATACode, date);


        int numberOfDepartingFlights = departingFlights.size();
        int numberOfArrivingFlights = arrivingFlights.size();

        if (!arrivingFlights.isEmpty()) {
            for (Flight flight : arrivingFlights) {
                totalNumberOfBaggageArriving += flight.getBaggages().stream()
                        .mapToInt(Baggage::getPieces)
                        .sum();
            }
        }

        if (!departingFlights.isEmpty()) {
            for (Flight flight : departingFlights) {
                totalNumberOfBaggageDeparting += flight.getBaggages().stream()
                        .mapToInt(Baggage::getPieces)
                        .sum();
            }
        }
        AirportDetails airportDetails = new AirportDetails(numberOfDepartingFlights, numberOfArrivingFlights,
                totalNumberOfBaggageArriving, totalNumberOfBaggageDeparting);
        return ResponseEntity.ok(airportDetails);
    }

}
