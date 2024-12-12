package com.example.backend.DTOs;

import com.example.backend.Enums.SeatClass;

public record TicketDTO(
    Integer userId,
    Integer flightId,
    SeatClass seatClass,
    int reservedSeats) {
        
}
