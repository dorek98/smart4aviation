package com.smart4aviation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long flightId;
    private int flightNumber;
    private String departureAirportIATACode;
    private String arrivalAirportIATACode;
    private OffsetDateTime departureDate;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<Baggage> baggages;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<Cargo> cargos;

    public Flight(long flightId, int flightNumber, String departureAirportIATACode, String arrivalAirportIATACode, OffsetDateTime departureDate) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.departureAirportIATACode = departureAirportIATACode;
        this.arrivalAirportIATACode = arrivalAirportIATACode;
        this.departureDate = departureDate;
    }

}
