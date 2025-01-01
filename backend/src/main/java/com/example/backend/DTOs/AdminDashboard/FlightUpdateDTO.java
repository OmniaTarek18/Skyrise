package com.example.backend.DTOs.AdminDashboard;

import java.time.LocalDate;
import java.util.List;

public record FlightUpdateDTO(
        Integer id,
        LocalDate arrivalDate,
        LocalDate departureDate,
        List<FlightLegUpdateDTO> flightLegs
) {
    
}
