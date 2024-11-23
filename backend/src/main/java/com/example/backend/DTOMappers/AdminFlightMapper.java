package com.example.backend.DTOMappers;

import com.example.backend.DTOs.AdminFlightDTO;
import com.example.backend.Entities.Flight;

public class AdminFlightMapper {

    public static AdminFlightDTO toDTO(Flight entity) {
        return new AdminFlightDTO(
                entity.getId(),
                entity.getSource(),
                entity.getDestination(),
                entity.getDepartureDate(),
                entity.getArrivalDate(),
                entity.getArrivalTime(),
                entity.getDepartureTime(),
                entity.getEconomyPrice(),
                entity.getBusinessPrice(),
                entity.getAvailableEconomySeats(),
                entity.getAvailableBusinessSeats(),
                entity.isCancel());
    }

    public static Flight toEntity(AdminFlightDTO dto) {
        Flight entity = Flight.builder()
                .id(dto.id())
                .source(dto.source())
                .destination(dto.destination())
                .arrivalTime(dto.arrivalTime())
                .departureTime(dto.departureTime())
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