package com.example.backend.DTOs;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record ReservationFilterCriteria(
        @NotNull(message= "User id cannot be null.")
        Integer userId,
        String source,
        String destination,
        LocalDate departureDate,
        LocalDate arrivalDate,
        Integer flightId,
        String sortBy,
        String direction,
        Boolean pastFlights,
        Boolean recentFlights) {

}