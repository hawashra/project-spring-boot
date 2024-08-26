package com.personal.flightsearch.services;

import com.personal.flightsearch.models.entities.Flight;
import com.personal.flightsearch.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlight(Long flightId) {
        return flightRepository.findById(flightId);
    }

    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public void deleteFlight(Long flightId) {

        if (flightRepository.findById(flightId).isEmpty()) {
            throw new RuntimeException("Flight not found with id: " + flightId);
        }

        flightRepository.deleteById(flightId);
    }

    public void updateFlight(Long flightId, Flight flight) {
        Optional<Flight> flightOptional = flightRepository.findById(flightId);

        if (flightOptional.isPresent()) {
            Flight flightToUpdate = flightOptional.get();
            flightToUpdate.setDepartureAirport(flight.getDepartureAirport());
            flightToUpdate.setArrivalAirport(flight.getArrivalAirport());
            flightToUpdate.setDepartureTime(flight.getDepartureTime());
            flightToUpdate.setReturnTime(flight.getReturnTime());
            flightToUpdate.setPrice(flight.getPrice());
            flightRepository.save(flightToUpdate);
        }
        else {
            throw new RuntimeException("Flight not found with id: " + flightId);
        }
    }
}
