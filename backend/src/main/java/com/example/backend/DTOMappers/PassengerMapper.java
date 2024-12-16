package com.example.backend.DTOMappers;

import com.example.backend.DTOs.PassengerDTO;
import com.example.backend.Entities.Passenger;
import com.example.backend.Entities.Reservation;

public class PassengerMapper {

    public static Passenger toEntity(PassengerDTO dto, Reservation reservation) {
        Passenger entity = Passenger.builder()
                .countryCode(dto.countryCode())
                .phoneNumber(dto.phoneNumber())
                .nationalId(dto.nationalId())
                .dateOfBirth(dto.dateOfBirth())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .gender(dto.gender())
                .passportNumber(dto.passportNumber())
                .passportIssuingCountry(dto.passportIssuingCountry())
                .reservation(reservation)
                .specialNeeds(dto.specialNeeds())
                .measlSpecification(dto.mealSpecification())
                .build();
        
        return entity;
    }
}
