package com.example.backend.DTOMappers;

import com.example.backend.DTOs.BookingDTOs.FlightLegDTO;
import com.example.backend.Entities.Airport;
import com.example.backend.Entities.FlightLeg;

public class FlightLegMapper {
    public static FlightLegDTO toDTO(FlightLeg entity) {
        Airport departureAiport = entity.getDepartureAirport();
        Airport arrivalAiport = entity.getArrivalAirport();
        return new FlightLegDTO(
                entity.getFlightId(),
                entity.getFlightLegId(),
                departureAiport.getId(),
                entity.getDepartureTime(),
                departureAiport.getAirportName(),
                departureAiport.getAirportCountry(),
                departureAiport.getAirportCity(),
                departureAiport.getAirportCode(),
                arrivalAiport.getId(),
                entity.getArrivalTime(),
                arrivalAiport.getAirportName(),
                arrivalAiport.getAirportCountry(),
                arrivalAiport.getAirportCity(),
                arrivalAiport.getAirportCode());
    }
}
