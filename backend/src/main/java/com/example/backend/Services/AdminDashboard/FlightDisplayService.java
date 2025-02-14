package com.example.backend.Services.AdminDashboard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.example.backend.DTOMappers.AdminFlightMapper;
import com.example.backend.DTOMappers.PageResponseMapper;
import com.example.backend.DTOs.PageResponse;
import com.example.backend.DTOs.AdminDashboard.AdminFlightDTO;
import com.example.backend.DTOs.AdminDashboard.FlightFilterCriteria;
import com.example.backend.Entities.Flight;
import com.example.backend.Repositories.FlightRepository;
import com.example.backend.Specifications.FlightSpecifications;
import com.example.backend.Utilites.Utilities;
import com.example.backend.Utilites.ValidateInput;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlightDisplayService {

    private final FlightRepository flightRepository;

    // dynamic filtering using specification to build our own criteria
    public PageResponse<AdminFlightDTO> filterFlights(FlightFilterCriteria flightFilterDTO, int pageNumber) {

        ValidateInput.validatePageNumber(pageNumber);

        Specification<Flight> spec = Specification.where(null);
        spec = spec.and(FlightSpecifications.hasDepartureDate(flightFilterDTO.departureDate()));

        if (flightFilterDTO.source() != null)
            spec = spec.and(FlightSpecifications.containsSource(flightFilterDTO.source()));

        if (flightFilterDTO.destination() != null)
            spec = spec.and(FlightSpecifications.containsDestination(flightFilterDTO.destination()));

        if (flightFilterDTO.status() != null) {
            String status = flightFilterDTO.status().toLowerCase();
            spec = spec.and(FlightSpecifications.containsStatus(status));
        }

        Sort sort = Utilities.sort(flightFilterDTO.direction(), flightFilterDTO.sortby());
        Pageable pageable = Utilities.CreatePage(pageNumber, 10, sort);

        Page<Flight> page = flightRepository.findAll(spec, pageable);
        Page<AdminFlightDTO> pageDTO = page.map(AdminFlightMapper::toDTO);
        return PageResponseMapper.toPageResponse(pageDTO);

    }

}
