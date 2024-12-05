package com.example.backend.DTOs;

import java.time.LocalDate;
import java.time.LocalTime;

public record AdminFlightDTO(
        Integer id,
        String source,
        String destination,
        LocalDate arrivalDate,
        LocalDate departureDate,
        LocalTime arrivalTime,
        LocalTime departureTime,
        float economyPrice,
        float businessPrice,
        int availableEconomySeats,
        int availableBusinessSeats,
        boolean isCancel) {
}
