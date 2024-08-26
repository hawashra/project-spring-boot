package com.personal.flightsearch.controllers;

import com.personal.flightsearch.mappers.Mapper;
import com.personal.flightsearch.models.dtos.AirportDTO;
import com.personal.flightsearch.models.entities.Airport;
import com.personal.flightsearch.services.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airports")
@RequiredArgsConstructor // to auto-inject final fields by adding them to the constructor.
public class AirportController {

    private final AirportService airportService;
    private final Mapper<Airport, AirportDTO> airportMapper;

    @GetMapping
    public ResponseEntity<List<AirportDTO>> getAllAirports() {
        try {
            return ResponseEntity.ok(airportService.getAllAirports().stream()
                    .map(airportMapper::map)
                    .toList()
            );
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Airport> getAirport(Long airportId) {
        if (airportId != null && airportService.getAirport(airportId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return airportService.getAirport(airportId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> saveAirport(Airport airport) {
        try {
            airportService.saveAirport(airport);
            return new ResponseEntity<>("Airport saved successfully",
                    org.springframework.http.HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Error saving airport",
                    org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAirport(@PathVariable Long airportId) {
        try {
            airportService.deleteAirport(airportId);
            return new ResponseEntity<>("Airport deleted successfully",
                    org.springframework.http.HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Error deleting airport",
                    org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Airport> updateAirport(@PathVariable Long airportId, @RequestBody Airport airport) {
        try {
            airportService.updateAirport(airportId, airport);
            return ResponseEntity.ok(airport);
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }


}
