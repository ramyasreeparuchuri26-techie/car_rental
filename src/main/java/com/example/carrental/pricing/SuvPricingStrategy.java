package com.example.carrental.pricing;

import org.springframework.stereotype.Component;

@Component
public class SuvPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(int days, double milesPerDay,int licenseYears) {
        return (15 * days) + (0.5 * milesPerDay * days);
    }
}