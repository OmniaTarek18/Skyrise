package com.example.backend.DTOs.AdminDashboard;

import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;

public record FlightLegUpdateDTO(
    @NotNull(message = "The flight leg id is required")
    Integer flightLegId,
    @NotNull(message = "The departure time is required")
    LocalTime departureTime,
    @NotNull(message = "The arrival time is required")
    LocalTime arrivalTime,
    @NotNull(message = "The departure airport id is required")
    Integer departureAirportId,
    @NotNull(message = "The arrival airport id is required")
    Integer arrivalAirportId
) {
    
}
