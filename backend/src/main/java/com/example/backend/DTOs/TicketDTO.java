package com.example.backend.DTOs;

import com.example.backend.Enums.SeatClass;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TicketDTO(
    @NotNull(message = "User id cannot be null.")
    Integer userId,
    @NotNull(message = "Flight id cannot be null.")
    Integer flightId,
    @NotNull(message = "Seat class cannot be null.")
    SeatClass seatClass,
    @Positive(message= "Number of reserved tickets must be greater than or equal to one.")
    int reservedSeats) {
        
}
