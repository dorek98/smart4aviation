package com.smart4aviation.service;

import com.smart4aviation.dto.BaggageRegistration;
import com.smart4aviation.dto.CargoRegistration;
import com.smart4aviation.dto.FlightCargoRegistration;
import com.smart4aviation.dto.FlightRegistration;
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
        Flight flight = new Flight(flightDTO.getFlightId() + 1, flightDTO.getFlightNumber(), flightDTO.getDepartureAirportIATACode(), flightDTO.getArrivalAirportIATACode(), flightDTO.getDepartureDate());
        flightRepository.save(flight);
    }

    @Override
    public void save(FlightCargoRegistration flightDTO) {
        Flight flight = flightRepository.getOne(flightDTO.getFlightId() + 1);
        List<BaggageRegistration> baggageRegistrationList = flightDTO.getBaggage();
        List<Baggage> baggageList = baggageRegistrationList.stream()
                .map(x -> new Baggage(x.getWeight(), x.getWeightUnit(), x.getPieces(), flight))
                .collect(Collectors.toList());
        baggageRespository.saveAll(baggageList);
        List<CargoRegistration> cargoRegistrationList = flightDTO.getCargo();
        List<Cargo> cargoList = cargoRegistrationList.stream()
                .map(x -> new Cargo(x.getWeight(), x.getWeightUnit(), x.getPieces(), flight))
                .collect(Collectors.toList());
        cargoRepository.saveAll(cargoList);
    }
}
