package com.example.backend.DTOs.AdminDashboard;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record FlightAddDTO(
    @NotNull(message = "The arrival date is required")
    LocalDate arrivalDate,
    @NotNull(message = "The departure date is required")
    LocalDate departureDate,
    @NotNull(message = "The economy price is required")
    float economyPrice,
    @NotNull(message = "The business price is required")
    float businessPrice,
    @NotNull(message = "The available economy seats are required")
    int availableEconomySeats,
    @NotNull(message = "The available business seats are required")
    int availableBusinessSeats,
    @NotNull(message = "The flight legs are required")
    List<@Valid FlightLegAddDTO> flightLegs
) {
    
}
