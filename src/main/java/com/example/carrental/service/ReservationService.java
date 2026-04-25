package com.example.carrental.service;


import com.example.carrental.model.OptionResponse;
import com.example.carrental.model.Reservation;
import com.example.carrental.model.ReservationRequest;
import com.example.carrental.model.VehicleType;
import com.example.carrental.pricing.PricingStrategy;
import com.example.carrental.pricing.PricingStrategyFactory;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ReservationService {

    // simple in-memory storage for lower environment
    private final Map<String, Reservation> reservationMap = new HashMap<>();
    private final PricingStrategyFactory factory;

    public ReservationService(PricingStrategyFactory factory) {
        this.factory = factory;
    }

    public Reservation reserve(ReservationRequest request) {

        PricingStrategy strategy =
                factory.getStrategy(request.getVehicleType());

        double amount = strategy.calculatePrice(
                request.getDays(),
                request.getMilesPerDay(),
                request.getLicenseYears()
        );

        Reservation reservation = new Reservation();
        reservation.setId(UUID.randomUUID().toString());
        reservation.setVehicleType(request.getVehicleType());
        reservation.setDays(request.getDays());
        reservation.setMilesPerDay(request.getMilesPerDay());
        reservation.setTotalAmount(amount);
        reservation.setLicenseYears(request.getLicenseYears());

        reservationMap.put(reservation.getId(), reservation);

        return reservation;
    }

    // 2) Modify reservation
    public Reservation modify(String id, ReservationRequest request) {
        Reservation existing = reservationMap.get(id);

        if (existing == null) {
            throw new RuntimeException("Reservation not found");
        }

        double amount = calculatePrice(
                request.getVehicleType(),
                request.getDays(),
                request.getMilesPerDay()
        );

        existing.setVehicleType(request.getVehicleType());
        existing.setDays(request.getDays());
        existing.setMilesPerDay(request.getMilesPerDay());
        existing.setTotalAmount(amount);

        return existing;
    }

    // 3) Cancel reservation
    public void cancel(String id) {
        if (!reservationMap.containsKey(id)) {
            throw new RuntimeException("Reservation not found");
        }

        reservationMap.remove(id);
    }

    // 4) Get options sorted by price
    public List<OptionResponse> getOptions(
            int days,
            double milesPerDay,
            int licenseYears) {

        List<OptionResponse> options = new ArrayList<>();

        for (VehicleType type : VehicleType.values()) {

            PricingStrategy strategy = factory.getStrategy(type);

            double amount = strategy.calculatePrice(
                    days,
                    milesPerDay,
                    licenseYears
            );

            options.add(new OptionResponse(type, amount));
        }

        options.sort(
                Comparator.comparingDouble(OptionResponse::getPrice)
        );
        return options;
    }

    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservationMap.values());
    }

}