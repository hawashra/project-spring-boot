package com.personal.flightsearch.controllers;

import com.personal.flightsearch.mappers.Mapper;
import com.personal.flightsearch.models.dtos.FlightDTO;
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
    private final Mapper<Flight, FlightDTO> flightMapper;

    @GetMapping
    public ResponseEntity<List<FlightDTO>> getAllFlights() {
        try {
            return ResponseEntity.ok(flightService
                    .getAllFlights()
                    .stream()
                    .map(flightMapper::map).toList());

        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<FlightDTO> getFlight(@PathVariable Long flightId) {
        if (flightId != null && flightService.getFlight(flightId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flightMapper.map(flightService.getFlight(flightId).get()));
    }

    @PostMapping
    public ResponseEntity<String> saveFlight(@RequestBody FlightDTO flightDTO) {

        try {
            flightService.saveFlight(flightMapper.reverseMap(flightDTO));
            return new ResponseEntity<>("Flight saved successfully",
                    org.springframework.http.HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Error saving flight",
                    org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{flightId}")
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

    @PutMapping("/{flightId}")
    public ResponseEntity<FlightDTO> updateFlight(@PathVariable Long flightId, @RequestBody FlightDTO flightDTO) {
        try {
            flightService.updateFlight(flightId, flightMapper.reverseMap(flightDTO));
            return ResponseEntity.ok(flightDTO);
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
