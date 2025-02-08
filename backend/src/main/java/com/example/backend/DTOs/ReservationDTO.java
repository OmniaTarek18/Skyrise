package com.example.backend.DTOs;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.backend.Enums.SeatClass;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ReservationDTO(
    @NotNull(message = "Flight id cannot be null.")
    Integer flightId,
    @NotNull(message = "User id cannot be null.")
    Integer userId,
    @NotBlank(message = "Source city cannot be null.")
    String source, 
    @NotBlank(message = "Destination city cannot be null.")
    String destination,
    @NotNull(message = "Departure date cannot be null.")
    LocalDate departureDate,
    @NotNull(message = "Departure time cannot be null.")
    LocalTime departureTime,
    @NotNull(message = "Arrival date cannot be null.")
    LocalDate arrivalDate,
    @NotNull(message = "Arrival time cannot be null.")
    LocalTime arrivalTime,
    @NotNull(message = "Seat class cannot be null.")
    SeatClass seatclass,
    @Positive(message = "Reserved seats cannot be null.")
    int reservedSeats,
    @PositiveOrZero(message = "Dismiss count cannot be negative.") 
    short dismissCount
    ) {
}
