package com.example.carrental.controller;

import com.example.carrental.model.OptionResponse;
import com.example.carrental.model.Reservation;
import com.example.carrental.model.ReservationRequest;
import com.example.carrental.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservationController {

    @Autowired
    private ReservationService service;

    // 1. Reserve
    @PostMapping("/reservations")
    public Reservation reserve(@RequestBody ReservationRequest request) {
        return service.reserve(request);
    }

    // 2. Modify
    @PutMapping("/reservations/{id}")
    public Reservation modify(@PathVariable String id,
                              @RequestBody ReservationRequest request) {
        return service.modify(id, request);
    }

    // 3. Cancel
    @DeleteMapping("/reservations/{id}")
    public String cancel(@PathVariable String id) {
        service.cancel(id);
        return "Cancelled";
    }

    // 4. Get Options
    @GetMapping("/options")
    public List<OptionResponse> getOptions(
            @RequestParam int days,
            @RequestParam double milesPerDay,
            @RequestParam int licenseYears) {

        return service.getOptions(days, milesPerDay, licenseYears);
    }

    @GetMapping("/reservations")
    public List<Reservation> getAllReservations() {
        return service.getAllReservations();
    }
}