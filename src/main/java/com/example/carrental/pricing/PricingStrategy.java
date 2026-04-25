package com.example.carrental.pricing;


public interface PricingStrategy {
    double calculatePrice(int days, double milesPerDay,int licenseYears);
}
