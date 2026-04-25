package com.example.carrental.pricing;

import org.springframework.stereotype.Component;

@Component
public class VanPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(int days, double milesPerDay,int licenseYears) {
        double base = (days < 10 ? 20 : 15) * days;
        return base * 1.10; // +10% cleaning fee
    }
}