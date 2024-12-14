package com.example.backend.Services;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.backend.DTOMappers.PageResponseMapper;
import com.example.backend.DTOMappers.ReservationMapper;
import com.example.backend.DTOs.PageResponse;
import com.example.backend.DTOs.ReservationDTO;
import com.example.backend.DTOs.ReservationFilterCriteria;
import com.example.backend.Entities.Reservation;
import com.example.backend.Repositories.ReservationRepository;
import com.example.backend.Specifications.ReservationSpecifications;
import com.example.backend.Utilites.ValidateInput;

@Service
public class ReservationDisplayService {
    
    private final ReservationRepository reservationRepository;

    public ReservationDisplayService (ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    public PageResponse<ReservationDTO> filterReserved (ReservationFilterCriteria filterDTO, int pageNumber) {
        ValidateInput.validatePageNumber(pageNumber);

        Specification<Reservation> spec = ReservationSpecifications.containsId("userId", filterDTO.userId());

        if (filterDTO.flightId() != null) {
            spec = spec.and(ReservationSpecifications.containsId("flightId", filterDTO.flightId()));
        }
        
        if (filterDTO.source() != null) {
            spec = spec.and(ReservationSpecifications.containsLocation("departureAirport", filterDTO.source()));
        }

        if (filterDTO.destination() != null) {
            spec = spec.and(ReservationSpecifications.containsLocation("arrivalAirport", filterDTO.destination()));
        }

        if (filterDTO.departureDate() != null) {
            spec = spec.and(ReservationSpecifications.containsDate("departureDate", filterDTO.departureDate()));
        }

        if (filterDTO.arrivalDate() != null) {
            spec = spec.and(ReservationSpecifications.containsDate("arrivalDate", filterDTO.arrivalDate()));
        }

        if (filterDTO.pastFlights() != null && filterDTO.pastFlights()) {
            spec = spec.and(ReservationSpecifications.beforeDate("arrivalDate", LocalDate.now()));
        }
        else if (filterDTO.recentFlights() != null && filterDTO.recentFlights()) {
            spec = spec.and(ReservationSpecifications.afterOrEqualDate("arrivalDate", LocalDate.now()));
        }

        if (filterDTO.sortBy() != null && filterDTO.direction() != null) {
            switch (filterDTO.sortBy().toLowerCase()) {
                case "departuredate" -> spec = spec.and(ReservationSpecifications.sortedByDate("departureDate", filterDTO.direction()));
                case "arrivaldate" -> spec = spec.and(ReservationSpecifications.sortedByDate("arrivalDate", filterDTO.direction()));
                case "flightid" -> spec = spec.and(ReservationSpecifications.sortedByFlightId(filterDTO.direction()));
                default -> {
                }
            }
        }

        Pageable pageable = PageRequest.of(pageNumber, 10);
        Page<Reservation> page = reservationRepository.findAll(spec, pageable);
        Page<ReservationDTO> pageDTO = page.map(ReservationMapper::toDTO);

        return PageResponseMapper.toPageResponse(pageDTO);
    }

}