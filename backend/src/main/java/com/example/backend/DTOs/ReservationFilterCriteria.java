package com.example.backend.DTOs;

import java.time.LocalDate;

public record ReservationFilterCriteria(
        Integer userId,
        String source,
        String destination,
        LocalDate departureDate,
        LocalDate arrivalDate,
        Integer flightId,
        String sortBy,
        String direction) {

}