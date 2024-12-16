package com.example.backend.Services.BookingProcess;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.backend.DTOMappers.FlightLegMapper;
import com.example.backend.DTOs.BookingDTOs.FlightLegDTO;
import com.example.backend.Entities.FlightLeg;
import com.example.backend.Repositories.FlightLegRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FlightLegDisplayService {
    private final FlightLegRepository flightLegRepository;

    public List<FlightLegDTO> getAllFlightLegs(Integer flightId) {
        List<FlightLeg> listOfFlightLeg = flightLegRepository.findByFlightId(flightId);
        List<FlightLegDTO> listOfFlightLegDTO = listOfFlightLeg.stream()
                .map(FlightLegMapper::toDTO)
                .collect(Collectors.toList());
        return listOfFlightLegDTO;
    }
}
