package com.example.backend.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.backend.DTOMappers.AirportMapper;
import com.example.backend.DTOs.AirportDTO;
import com.example.backend.Entities.Airport;
import com.example.backend.Repositories.AirportRepository;

@Service
public class AirportDisplayService {

    private final AirportRepository airportRepository;

    public AirportDisplayService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<AirportDTO> getAllAirports() {

        List<Airport> airports = airportRepository.findAll();
        List<AirportDTO> airportDTOs = airports.stream().map(AirportMapper::toDTO).collect(Collectors.toList());

        return airportDTOs;
    }
}
