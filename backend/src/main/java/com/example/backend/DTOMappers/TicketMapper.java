package com.example.backend.DTOMappers;

import com.example.backend.DTOs.TicketDTO;
import com.example.backend.Entities.Reservation;

public class TicketMapper {

    public static Reservation toEntity(TicketDTO dto) {
        Reservation entity = Reservation.builder()
                .userId(dto.userId())
                .flightId(dto.flightId())
                .seatClass(dto.seatClass())
                .reservedSeats(dto.reservedSeats())
                .build();
        return entity;
    }
}
