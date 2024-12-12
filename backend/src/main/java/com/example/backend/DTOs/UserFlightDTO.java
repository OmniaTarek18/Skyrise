package com.example.backend.DTOs;

import java.time.LocalDate;
import java.time.LocalTime;

public record UserFlightDTO (
    Integer id,
    String source,      // "City, Country"
    String destination, // "City, Country"
    LocalDate arrivalDate,
    LocalTime arrivalTime,
    LocalDate departureDate,
    LocalTime departureTime
    ) { 
}