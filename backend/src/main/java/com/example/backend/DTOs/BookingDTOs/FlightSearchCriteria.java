package com.example.backend.DTOs.BookingDTOs;

import java.time.LocalDate;

import com.example.backend.Enums.FlightType;
import com.example.backend.Enums.SeatClass;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record FlightSearchCriteria(
        @NotNull(message = "The arrival airport id is required")
        Integer arrivalAirportId,

        @NotNull(message = "The departure airport id is required")
        Integer departureAirportId,
        
        @NotNull(message = "The departure date is required")
        @FutureOrPresent(message = "The departure date can't be in the past")
        LocalDate departureDate,

        @NotNull(message = "The seat class is required")
        SeatClass seatClass,

        @Positive(message = "Number of tickets is required and must be positive")
        int numberOfTickets,
        
        FlightType flightType,
        String sortby,
        String direction) {

}
