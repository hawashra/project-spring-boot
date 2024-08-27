package com.personal.flightsearch.mappers;

import com.personal.flightsearch.models.dtos.AirportDTO;
import com.personal.flightsearch.models.entities.Airport;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AirportMapper implements Mapper<Airport, AirportDTO> {

    private final ModelMapper modelMapper;

    public AirportMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AirportDTO map(Airport airport) {
        return modelMapper.map(airport, AirportDTO.class);
    }

    @Override
    public Airport reverseMap(AirportDTO airportDTO) {
        return modelMapper.map(airportDTO, Airport.class);
    }
}
