package com.example.backend.Services;

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

    public PageResponse<ReservationDTO> getReservationByUserId (Integer userId, int pageNumber) {
        ValidateInput.validateId(userId);
        ValidateInput.validatePageNumber(pageNumber);

        Pageable pageable = PageRequest.of(pageNumber, 10);
        Page<Reservation> page = reservationRepository.findByUserId(userId, pageable);
        Page<ReservationDTO> pageDTO = page.map(ReservationMapper :: toDTO);
        return PageResponseMapper.toPageResponse(pageDTO);
    }

    public PageResponse<ReservationDTO> filterReserved (ReservationFilterCriteria filterDTO, int pageNumber) {
        ValidateInput.validatePageNumber(pageNumber);

        Specification<Reservation> spec = ReservationSpecifications.containsSource(filterDTO.source());
        spec = spec.and(ReservationSpecifications.containsDestination(filterDTO.destination()));

        if (filterDTO.departureDate() != null) {
            spec = spec.and(ReservationSpecifications.containsDepartureDate(filterDTO.departureDate()));
        }

        if (filterDTO.arrivalDate() != null) {
            spec = spec.and(ReservationSpecifications.containsArrivalDate(filterDTO.arrivalDate()));
        }

        if (filterDTO.flightId() != null) {
            spec = spec.and(ReservationSpecifications.conainsFlightId(filterDTO.flightId()));
        }

        if (filterDTO.sortBy() != null && filterDTO.direction() != null) {
            if (filterDTO.sortBy().toLowerCase().equals("departuredate")) {
                spec = spec.and(ReservationSpecifications.sortedByDepartureDate(filterDTO.direction()));
            }
            else if (filterDTO.sortBy().toLowerCase().equals("arrivaldate")) {
                spec = spec.and(ReservationSpecifications.sortedByArrivalDate(filterDTO.direction()));
            }
        }

        Pageable pageable = PageRequest.of(pageNumber, 10);
        Page<Reservation> page = reservationRepository.findAll(spec, pageable);
        Page<ReservationDTO> pageDTO = page.map(ReservationMapper::toDTO);

        return PageResponseMapper.toPageResponse(pageDTO);
    }

}
