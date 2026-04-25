package com.example.carrental.pricing;

import org.springframework.stereotype.Component;

@Component
public class PickupTruckPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(int days, double milesPerDay, int licenseYears) {
        double base = 30 * days;

        // surge fee
        if (licenseYears < 3) {
            base = base + (base * 0.10);
        }

        return base;
    }
}
