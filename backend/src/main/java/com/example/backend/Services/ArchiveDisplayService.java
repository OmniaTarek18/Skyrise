package com.example.backend.Services;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.backend.DTOMappers.AdminFlightMapper;
import com.example.backend.DTOMappers.PageResponseMapper;
import com.example.backend.DTOs.AdminFlightDTO;
import com.example.backend.DTOs.FlightFilterCriteria;
import com.example.backend.DTOs.PageResponse;
import com.example.backend.Entities.Flight;
import com.example.backend.Repositories.FlightRepository;
import com.example.backend.Specifications.FlightSpecifications;
import com.example.backend.Utilites.ValidateInput;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArchiveDisplayService {

    private final FlightRepository flightRepository;

    public PageResponse<AdminFlightDTO> getAllPastFlights(int pageNumber) {
        ValidateInput.validatePageNumber(pageNumber);

        LocalDate today = LocalDate.now();
        Pageable pageable = PageRequest.of(pageNumber, 10);
        Page<Flight> page = flightRepository.findByArrivalDateLessThan(today, pageable);
        Page<AdminFlightDTO> pageDTO = page.map(AdminFlightMapper::toDTO);
        return PageResponseMapper.toPageResponse(pageDTO);

    }

    public PageResponse<AdminFlightDTO> filterPastFlights(FlightFilterCriteria flightFilterDTO, int pageNumber) {

        ValidateInput.validatePageNumber(pageNumber);
        LocalDate today = LocalDate.now();
        Specification<Flight> spec = FlightSpecifications.hasArrivalDateLessThan(today);

        if (flightFilterDTO.source() != null)
            spec = spec.and(FlightSpecifications.containsSource(flightFilterDTO.source()));

        if (flightFilterDTO.destination() != null)
            spec = spec.and(FlightSpecifications.containsDestination(flightFilterDTO.destination()));
        
        Pageable pageable = PageRequest.of(pageNumber, 10);
        Page<Flight> page = flightRepository.findAll(spec, pageable);
        Page<AdminFlightDTO> pageDTO = page.map(AdminFlightMapper::toDTO);
        return PageResponseMapper.toPageResponse(pageDTO);
    }
}
