package com.personal.flightsearch.controllers;

import com.personal.flightsearch.models.entities.Flight;
import com.personal.flightsearch.services.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        try {
            return ResponseEntity.ok(flightService.getAllFlights());
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<Flight> getFlight(@PathVariable Long flightId) {
        if (flightId != null && flightService.getFlight(flightId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return flightService.getFlight(flightId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> saveFlight(@RequestBody Flight flight) {

        try {
            flightService.saveFlight(flight);
            return new ResponseEntity<>("Flight saved successfully",
                    org.springframework.http.HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Error saving flight",
                    org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping
    public ResponseEntity<String> deleteFlight(@PathVariable Long flightId) {
        try {
            flightService.deleteFlight(flightId);
            return new ResponseEntity<>("Flight deleted successfully",
                    org.springframework.http.HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Error deleting flight",
                    org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Flight> updateFlight(@PathVariable Long flightId, @RequestBody Flight flight) {
        try {
            flightService.updateFlight(flightId, flight);
            return ResponseEntity.ok(flight);
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
