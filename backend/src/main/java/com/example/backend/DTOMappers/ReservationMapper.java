package com.example.backend.DTOMappers;

import com.example.backend.DTOs.ReservationDTO;
import com.example.backend.Entities.FlightLeg;
import com.example.backend.Entities.Reservation;

public class ReservationMapper {
    public static ReservationDTO toDTO(Reservation entity) {
        int numOfLegs = entity.getFlight().getFlightLegs().size();
        FlightLeg first = entity.getFlight().getFlightLegs().get(0);
        FlightLeg last = entity.getFlight().getFlightLegs().get(numOfLegs - 1);
        String source = first.getDepartureAirport().getAirportCity().concat(", ");
        String destination = last.getArrivalAirport().getAirportCity().concat(", ");

        return new ReservationDTO(
                entity.getFlightId(),
                entity.getUserId(),
                source.concat(first.getDepartureAirport().getAirportCountry()),
                destination.concat(last.getArrivalAirport().getAirportCountry()),
                entity.getFlight().getDepartureDate(), 
                first.getDepartureTime(), 
                entity.getFlight().getArrivalDate(), 
                last.getArrivalTime(), 
                entity.getSeatClass(), 
                entity.getReservedSeats(),
                entity.getDismissCount());
    }
}