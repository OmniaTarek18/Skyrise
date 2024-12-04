package com.example.backend.DTOMappers;

import com.example.backend.DTOs.AirportDTO;
import com.example.backend.Entities.Airport;

public class AirportMapper {
    public static AirportDTO toDTO(Airport entity) {
        return new AirportDTO(
                entity.getId(),
                entity.getAirportName(),
                entity.getAirportCity(),
                entity.getAirportCountry(),
                entity.getAirportCode(),
                entity.getLatitude(),
                entity.getLongitude());
    }

    public static Airport toEntity(AirportDTO dto) {
        Airport entity = Airport.builder()
                .id(dto.id())
                .airportName(dto.airportName())
                .airportCity(dto.airportCity())
                .airportCountry(dto.airportCountry())
                .airportCode(dto.airportCode())
                .latitude(dto.latitude())
                .longitude(dto.longitude())
                .build();

        return entity;
    }
}
