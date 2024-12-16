package com.example.backend.DTOs.BookingDTOs;

import java.time.LocalTime;

public record FlightLegDTO(
        Integer flightId,
        Integer flightLegId,
        LocalTime departureTime,
        String departureAirportName,
        String departureAirportCountry,
        String departureAirportCity,
        String departureAirportCode,
        LocalTime arrivalTime,
        String arrivalAirportName,
        String arrivalAirportCountry,
        String arrivalAirportCity,
        String arrivalAirportCode) {

}
