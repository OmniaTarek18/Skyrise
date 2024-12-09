package com.example.backend.DTOs.BookingDTOs;

import java.time.LocalDate;

import com.example.backend.Enums.FlightType;
import com.example.backend.Enums.SeatClass;

public record FlightSearchCriteria(
        // the first five attributes can't be null
        Integer arrivalAirportId,
        Integer departureAirportId,
        LocalDate departureDate,
        SeatClass seatClass,
        int numberOfTickets,
        FlightType flightType,
        String sortby,
        String direction) {

}
