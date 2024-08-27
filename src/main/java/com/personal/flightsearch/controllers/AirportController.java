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

    @GetMapping("/{airportId}")
    public ResponseEntity<AirportDTO> getAirport(@PathVariable Long airportId) {
        if (airportId != null && airportService.getAirport(airportId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(airportMapper.map(airportService.getAirport(airportId).get()));
    }

    @PostMapping
    public ResponseEntity<String> saveAirport(AirportDTO airportDTO) {
        try {
            airportService.saveAirport(airportMapper.reverseMap(airportDTO));
            return new ResponseEntity<>("Airport saved successfully",
                    org.springframework.http.HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Error saving airport",
                    org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{airportId}")
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

    @PutMapping("/{airportId}")
    public ResponseEntity<AirportDTO> updateAirport(@PathVariable Long airportId,
                                                    @RequestBody AirportDTO airportDTO) {
        try {
            airportService.updateAirport(airportId, airportMapper.reverseMap(airportDTO));
            return ResponseEntity.ok(airportDTO);
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
