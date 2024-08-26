package com.personal.flightsearch.services;

import com.personal.flightsearch.models.entities.Airport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.personal.flightsearch.repositories.AirportRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public Optional<Airport> getAirport(Long airportId) {
        return airportRepository.findById(airportId);
    }

    public void deleteAirport(Long airportId) {

        if (airportRepository.findById(airportId).isEmpty()) {
            throw new RuntimeException("Airport not found with id: " + airportId);
        }

        airportRepository.deleteById(airportId);
    }

    public void updateAirport(Long airportId, Airport airport) {
        Optional<Airport> airportOptional = airportRepository.findById(airportId);

        if (airportOptional.isPresent()) {
            Airport airportToUpdate = airportOptional.get();
            airportToUpdate.setCity(airport.getCity());
            airportRepository.save(airportToUpdate);
        }
        else {
            throw new RuntimeException("Airport not found with id: " + airportId);
        }
    }

}
