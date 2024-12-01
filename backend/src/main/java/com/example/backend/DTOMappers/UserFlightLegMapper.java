package com.example.backend.DTOMappers;

import com.example.backend.DTOs.UserFlightLegDTO;
import com.example.backend.Entities.FlightLeg;

public class UserFlightLegMapper {

    public static UserFlightLegDTO toDTO(FlightLeg entity) {
        String source = entity.getDepartureAirport().getAirportCity().concat(", ");
        String destination = entity.getArrivalAirport().getAirportCity().concat(", ");
        
        return new UserFlightLegDTO(
                entity.getFlightId(),
                entity.getFlightLegId(),
                source.concat(entity.getDepartureAirport().getAirportCountry()),
                entity.getDepartureAirport().getAirportName(),
                destination.concat(entity.getArrivalAirport().getAirportCountry()),
                entity.getArrivalAirport().getAirportName(),
                entity.getArrivalTime(),
                entity.getDepartureTime());
    }
}
