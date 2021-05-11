package com.smart4aviation.dto;

import com.smart4aviation.model.WeightUnit;
import lombok.Value;

@Value
public class CargoRegistration {
    long id;
    int weight;
    WeightUnit weightUnit;
    int pieces;
}