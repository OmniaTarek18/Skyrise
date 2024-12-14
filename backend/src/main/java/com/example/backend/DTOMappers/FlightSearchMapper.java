package com.example.backend.DTOMappers;

import com.example.backend.DTOs.BookingDTOs.FlightSearchResponse;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.FlightLeg;
import com.example.backend.Enums.SeatClass;

public class FlightSearchMapper {

    public static FlightSearchResponse toDTO(Flight entity, SeatClass seatClass) {

        int numberOfLegs = entity.getFlightLegs().size();
        FlightLeg first = entity.getFlightLegs().get(0);
        FlightLeg last = entity.getFlightLegs().get(numberOfLegs - 1);
        float price = 0;

        if(seatClass == SeatClass.ECONOMY)
            price = entity.getEconomyPrice();
        else 
            price = entity.getBusinessPrice();

        return new FlightSearchResponse(
                entity.getId(),
                entity.getArrivalDate(),
                entity.getDepartureDate(),
                last.getArrivalTime(),
                first.getDepartureTime(),
                price);
    }
}
