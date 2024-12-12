package com.example.backend.DTOMappers;

import com.example.backend.DTOs.UserFlightDTO;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.FlightLeg;

public class UserFlightMapper {
    
    public static UserFlightDTO toDto (Flight entity) {

        int numberOfLegs = entity.getFlightLegs().size();
        FlightLeg first = entity.getFlightLegs().get(0); 
        FlightLeg last = entity.getFlightLegs().get(numberOfLegs-1);
        String source = first.getDepartureAirport().getAirportCity().concat(", ");
        String destination = last.getArrivalAirport().getAirportCity().concat(", ");

        return new UserFlightDTO(
                entity.getId(),
                source.concat(first.getDepartureAirport().getAirportCountry()),
                destination.concat(last.getArrivalAirport().getAirportCountry()),
                entity.getArrivalDate(),
                last.getArrivalTime(),
                entity.getDepartureDate(),
                first.getDepartureTime());
    }
}