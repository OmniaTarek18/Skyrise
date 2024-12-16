package com.example.backend.DTOMappers;

import com.example.backend.DTOs.AdminDashboard.AdminFlightDTO;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.FlightLeg;

public class AdminFlightMapper {

    public static AdminFlightDTO toDTO(Flight entity) {

        int numberOfLegs = entity.getFlightLegs().size();
        FlightLeg first = entity.getFlightLegs().get(0); 
        FlightLeg last = entity.getFlightLegs().get(numberOfLegs-1); 

        return new AdminFlightDTO(
                entity.getId(),
                first.getDepartureAirport().getAirportCity(),
                last.getArrivalAirport().getAirportCity(),
                entity.getArrivalDate(),
                entity.getDepartureDate(),
                last.getArrivalTime(),
                first.getDepartureTime(),
                entity.getEconomyPrice(),
                entity.getBusinessPrice(),
                entity.getAvailableEconomySeats(),
                entity.getAvailableBusinessSeats(),
                entity.isCancel());
    }

    public static Flight toEntity(AdminFlightDTO dto) {
        Flight entity = Flight.builder()
                .id(dto.id())
                .departureDate(dto.departureDate())
                .arrivalDate(dto.arrivalDate())
                .economyPrice(dto.economyPrice())
                .businessPrice(dto.businessPrice())
                .availableBusinessSeats(dto.availableBusinessSeats())
                .availableEconomySeats(dto.availableEconomySeats())
                .isCancel(dto.isCancel())
                .build();
        return entity;
    }
}