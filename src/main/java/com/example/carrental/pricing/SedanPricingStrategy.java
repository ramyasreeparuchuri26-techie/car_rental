package com.example.carrental.pricing;
import org.springframework.stereotype.Component;

@Component
public class SedanPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(int days, double milesPerDay,int licenseYears) {
        return (days < 10 ? 20 : 15) * days;
    }
}
