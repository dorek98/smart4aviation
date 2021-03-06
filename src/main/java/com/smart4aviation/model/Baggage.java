package com.smart4aviation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Baggage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int weight;
    @Enumerated(EnumType.STRING)
    private WeightUnit weightUnit;
    private int pieces;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    public Baggage(int weight, WeightUnit weightUnit, int pieces, Flight flight) {
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.pieces = pieces;
        this.flight = flight;
    }
}
