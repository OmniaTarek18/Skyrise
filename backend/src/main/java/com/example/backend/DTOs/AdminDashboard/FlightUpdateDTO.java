package com.example.backend.DTOs.AdminDashboard;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record FlightUpdateDTO(
        @NotNull(message = "The flight id is required")
        Integer id,
        @NotNull(message = "The arrival date is required")
        LocalDate arrivalDate,
        @NotNull(message = "The departure date is required")
        LocalDate departureDate,
        @NotNull(message = "The flight legs are required")
        List<@Valid FlightLegUpdateDTO> flightLegs
) {
    
}
