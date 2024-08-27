package com.personal.flightsearch.mappers;

import com.personal.flightsearch.models.dtos.FlightDTO;
import com.personal.flightsearch.models.entities.Flight;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper implements Mapper<Flight, FlightDTO> {

    private final ModelMapper modelMapper;

    public FlightMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public FlightDTO map(Flight flight) {
        return modelMapper.map(flight, FlightDTO.class);
    }

    @Override
    public Flight reverseMap(FlightDTO flightDTO) {
        return modelMapper.map(flightDTO, Flight.class);
    }
}
