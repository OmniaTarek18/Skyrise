package com.example.backend.Services;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.backend.DTOMappers.AdminFlightMapper;
import com.example.backend.DTOMappers.PageResponseMapper;
import com.example.backend.DTOs.AdminFlightDTO;
import com.example.backend.DTOs.PageResponse;
import com.example.backend.Entities.Flight;
import com.example.backend.Repositories.FlightRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArchiveDisplayService {

    private final FlightRepository flightRepository;

    public PageResponse<AdminFlightDTO> getAllPastFligths(int pageNumber) {

        LocalDate today = LocalDate.now();
        Pageable pageable = PageRequest.of(pageNumber, 10);
        Page<Flight> page = flightRepository.findByArrivalDateLessThan(today, pageable);
        Page<AdminFlightDTO> pageDTO = page.map(AdminFlightMapper::toDTO);
        return PageResponseMapper.toPageResponse(pageDTO);

    }

    // public PageResponse<AdminFlightDTO> filterPastFlights() {
        
    // }
}
