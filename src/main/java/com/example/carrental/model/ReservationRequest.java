package com.example.carrental.model;

import lombok.Data;

@Data
public class ReservationRequest {
    private VehicleType vehicleType;
    private int days;
    private double milesPerDay;
    private int licenseYears;

    // getters/setters
}
