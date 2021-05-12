package com.smart4aviation.service;

import com.smart4aviation.dto.cargo.FlightCargoRegistration;
import com.smart4aviation.dto.flight.FlightRegistration;
import com.smart4aviation.model.Baggage;
import com.smart4aviation.model.Cargo;
import com.smart4aviation.model.Flight;
import com.smart4aviation.repository.BaggageRespository;
import com.smart4aviation.repository.CargoRepository;
import com.smart4aviation.repository.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class FlightCommandHandlerImpl implements FlightCommandHandler {

    private final FlightRepository flightRepository;
    private final BaggageRespository baggageRespository;
    private final CargoRepository cargoRepository;

    @Override
    public void save(FlightRegistration flightDTO) {
        Flight flight = new Flight(flightDTO.getFlightId() + 1, flightDTO.getFlightNumber(),
                flightDTO.getDepartureAirportIATACode(), flightDTO.getArrivalAirportIATACode(), flightDTO.getDepartureDate());
        flightRepository.save(flight);
    }

    @Override
    public void save(FlightCargoRegistration flightDTO) {
        Flight flight = flightRepository.getOne(flightDTO.getFlightId() + 1);

        List<Baggage> baggageList = flightDTO.getBaggage().stream()
                .map(baggage -> new Baggage(baggage.getWeight(), baggage.getWeightUnit(), baggage.getPieces(), flight))
                .collect(Collectors.toList());
        baggageRespository.saveAll(baggageList);


        List<Cargo> cargoList = flightDTO.getCargo().stream()
                .map(cargo -> new Cargo(cargo.getWeight(), cargo.getWeightUnit(), cargo.getPieces(), flight))
                .collect(Collectors.toList());
        cargoRepository.saveAll(cargoList);
    }
}
