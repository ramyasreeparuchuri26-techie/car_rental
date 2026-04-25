package com.example.carrental.model;

import lombok.Data;

@Data
public class Reservation {
    private String id;
    private VehicleType vehicleType;
    private int days;
    private double milesPerDay;
    private double totalAmount;
    private int licenseYears;

    // getters/setters
}
