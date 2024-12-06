package com.example.backend.DTOs;

public record InsightsDTO(
        long numOfUsers,
        long numOfActiveFlights,
        long numOfReservations) {
}
