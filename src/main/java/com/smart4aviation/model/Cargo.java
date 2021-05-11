package com.smart4aviation.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
public class Cargo {
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

    public Cargo(int weight, WeightUnit weightUnit, int pieces, Flight flight) {
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.pieces = pieces;
        this.flight = flight;
    }
}