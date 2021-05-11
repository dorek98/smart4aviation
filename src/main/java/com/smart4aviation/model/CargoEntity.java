//package com.smart4aviation.model;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.List;
//
//
//@Entity
//@Table(name = "cargo_entity")
//@Data
//@NoArgsConstructor
//public class CargoEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long cargoEntityId;
//
//    @OneToOne(mappedBy = "cargo_entity")
//    private Flight flight;
//
//    @OneToMany(mappedBy = "cargo_entity")
//    private List<Baggage> baggages;
//
//    @OneToMany(mappedBy = "cargo_entity")
//    private List<Cargo> cargos;
//}
