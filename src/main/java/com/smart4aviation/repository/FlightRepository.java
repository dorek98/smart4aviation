package com.smart4aviation.repository;

import com.smart4aviation.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    Optional<Flight> findByFlightNumberAndDepartureDate(int flightNumber, OffsetDateTime departureDate);
    List<Flight> findByArrivalAirportIATACodeAndDepartureDate(String airportIATACode, OffsetDateTime date);
    List<Flight> findByDepartureAirportIATACodeAndDepartureDate(String airportIATACode, OffsetDateTime date);
}
