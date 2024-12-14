package com.example.backend.DTOs.AdminDashboard;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record FlightFilterCriteria(
        @NotNull(message = "You must enter the departure date")
        LocalDate departureDate,
        String source,
        String destination,
        String status,
        String sortby,
        String direction) {
}
