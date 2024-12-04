package com.example.backend.DTOs;

public record AirportDTO(
    Integer id,
    String airportName,
    String airportCity,
    String airportCountry,
    String airportCode,
    Double latitude,
    Double longitude
    ) {
}
