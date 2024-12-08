package com.example.backend.DTOs;

import java.time.LocalDate;

public record FlightFilterCriteria(
        LocalDate departureDate,
        String source,
        String destination,
        String status,
        String sortby,
        String direction) {
}
