package com.personal.flightsearch.models.dtos;

import java.time.LocalDateTime;

public class FlightDTO {
    private Long id;
    private Long departureAirportId;
    private Long arrivalAirportId;
    private LocalDateTime departureTime;
    private LocalDateTime returnTime;
    private Double price;
}
