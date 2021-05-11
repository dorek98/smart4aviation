package com.smart4aviation.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long flightId;
    private int flightNumber;
    private String departureAirportIATACode;
    private String arrivalAirportIATACode;
    private String departureDate;

    @OneToMany(mappedBy = "flight")
    private List<Baggage> baggages;

    @OneToMany(mappedBy = "flight")
    private List<Cargo> cargos;

    public Flight(long flightId, int flightNumber, String departureAirportIATACode, String arrivalAirportIATACode, String departureDate) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.departureAirportIATACode = departureAirportIATACode;
        this.arrivalAirportIATACode = arrivalAirportIATACode;
        this.departureDate = departureDate;
    }

}
