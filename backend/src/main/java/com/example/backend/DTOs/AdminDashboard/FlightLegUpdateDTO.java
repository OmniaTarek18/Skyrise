package com.example.backend.DTOs.AdminDashboard;

import java.time.LocalTime;

public record FlightLegUpdateDTO(
    Integer flightLegId,
    LocalTime departureTime,
    LocalTime arrivalTime,
    Integer departureAirportId,
    Integer arrivalAirportId
) {
    
}
