package com.example.backend.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AirportDTO(
    @NotNull(message = "Airport id cannot be null.")
    Integer id,
    @NotBlank(message = "Airport name cannot be null.")
    String airportName,
    @NotBlank(message = "Airport city cannot be null.")
    String airportCity,
    @NotBlank(message = "Airport country cannot be null.")
    String airportCountry,
    @NotBlank(message = "Airport code cannot be null.")
    String airportCode,
    Double latitude,
    Double longitude
    ) {
}