package com.example.carrental.pricing;

import com.example.carrental.model.VehicleType;
import org.springframework.stereotype.Component;

@Component
public class PricingStrategyFactory {

    private final SedanPricingStrategy sedan;
    private final SuvPricingStrategy suv;
    private final VanPricingStrategy van;
    private final PickupTruckPricingStrategy pickup;

    public PricingStrategyFactory(
            SedanPricingStrategy sedan,
            SuvPricingStrategy suv,
            VanPricingStrategy van,
            PickupTruckPricingStrategy pickup) {

        this.sedan = sedan;
        this.suv = suv;
        this.van = van;
        this.pickup = pickup;
    }

    public PricingStrategy getStrategy(VehicleType type) {
        switch (type) {
            case SEDAN:
                return sedan;
            case SUV:
                return suv;
            case VAN:
                return van;
            case PICKUP_TRUCK:
                return pickup;
            default:
                throw new RuntimeException("Invalid vehicle type");
        }
    }
}