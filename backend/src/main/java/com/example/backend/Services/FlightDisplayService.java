package com.example.backend.Services;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.example.backend.DTOMappers.AdminFlightMapper;
import com.example.backend.DTOMappers.PageResponseMapper;
import com.example.backend.DTOs.AdminFlightDTO;
import com.example.backend.DTOs.FlightFilterCriteria;
import com.example.backend.DTOs.PageResponse;
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

    public PageResponse<AdminFlightDTO> getFlights(LocalDate departureDate, int pageNumber) {

        ValidateInput.validatePageNumber(pageNumber);
        ValidateInput.validateDepartureDate(departureDate);

        Pageable pageable = PageRequest.of(pageNumber, 10);
        Page<Flight> page = flightRepository.findByDepartureDate(departureDate, pageable);
        Page<AdminFlightDTO> pageDTO = page.map(AdminFlightMapper::toDTO);
        return PageResponseMapper.toPageResponse(pageDTO);

    }

    // dynamic filtering using specification to build our own criteria
    public PageResponse<AdminFlightDTO> filterFlights(FlightFilterCriteria flightFilterDTO, int pageNumber) {

        ValidateInput.validatePageNumber(pageNumber);
        Specification<Flight> spec = Specification.where(null);

        if (flightFilterDTO.departureDate() != null)
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

        Pageable pageable = (sort != null) ? PageRequest.of(pageNumber, 10, sort)
                : PageRequest.of(pageNumber, 10);

        Page<Flight> page = flightRepository.findAll(spec, pageable);
        Page<AdminFlightDTO> pageDTO = page.map(AdminFlightMapper::toDTO);
        return PageResponseMapper.toPageResponse(pageDTO);

    }

}
