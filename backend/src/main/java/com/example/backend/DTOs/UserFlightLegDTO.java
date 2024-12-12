package com.example.backend.DTOs;

import java.time.LocalTime;

public record UserFlightLegDTO(
    Integer flightId,
    Integer flightLegId,
    String source,                // "City, Country"
    String departureAirportName,
    String destination,           // "City, Country"
    String arrivalAirportName,
    LocalTime arrivalTime, 
    LocalTime departureTime
    ) {
}