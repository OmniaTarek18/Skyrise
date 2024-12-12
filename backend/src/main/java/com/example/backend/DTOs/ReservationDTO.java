package com.example.backend.DTOs;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.backend.Enums.SeatClass;

public record ReservationDTO(
    Integer flightId,
    Integer userId,
    String source, 
    String destination,
    LocalDate departureDate,
    LocalTime departureTime,
    LocalDate arrivalDate,
    LocalTime arrivalTime,
    SeatClass seatclass,
    int reservedSeats
    ) {
}
