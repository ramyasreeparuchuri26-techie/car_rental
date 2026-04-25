package com.example.carrental.model;

import lombok.Data;

@Data
public class OptionResponse {
    private VehicleType vehicleType;
    private double price;

    public OptionResponse(VehicleType vehicleType, double price) {
        this.vehicleType = vehicleType;
        this.price = price;
    }

    // getters/setters
}
