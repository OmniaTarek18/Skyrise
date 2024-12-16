package com.example.backend.Services.BookingProcess;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.example.backend.DTOMappers.FlightSearchMapper;
import com.example.backend.DTOMappers.PageResponseMapper;
import com.example.backend.DTOs.PageResponse;
import com.example.backend.DTOs.BookingDTOs.FlightSearchCriteria;
import com.example.backend.DTOs.BookingDTOs.FlightSearchResponse;
import com.example.backend.Entities.Flight;
import com.example.backend.Enums.SeatClass;
import com.example.backend.Repositories.FlightRepository;
import com.example.backend.Specifications.FlightSpecifications;
import com.example.backend.Utilites.Utilities;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FlightSearchService {
        private final FlightRepository flightRepository;

        public PageResponse<FlightSearchResponse> getFlights(FlightSearchCriteria flightSearchCriteria,
                        int pageNumber) {

                Specification<Flight> spec = Specification.where(null);

                spec = spec.and(FlightSpecifications.hasDepartureDate(flightSearchCriteria.departureDate()));
                spec = spec.and(FlightSpecifications.hasDepartureAirport(flightSearchCriteria.departureAirportId()));
                spec = spec.and(FlightSpecifications.hasArrivalAirport(flightSearchCriteria.arrivalAirportId()));
                spec = spec.and(FlightSpecifications.hasAvailableSeats(flightSearchCriteria.seatClass(),
                                flightSearchCriteria.numberOfTickets()));

                if (flightSearchCriteria.flightType() != null)
                        spec = spec.and(FlightSpecifications.hasFlightType(flightSearchCriteria.flightType()));

                // fliter and pagination part
                Sort sort = handleSorting(flightSearchCriteria.sortby(),
                                flightSearchCriteria.direction(),
                                flightSearchCriteria.seatClass());

                Pageable pageable = Utilities.CreatePage(pageNumber, 10, sort);

                Page<Flight> page = flightRepository.findAll(spec, pageable);
                Page<FlightSearchResponse> pageDTO = page.map(
                                flight -> FlightSearchMapper.toDTO(flight, flightSearchCriteria.seatClass()));
                return PageResponseMapper.toPageResponse(pageDTO);

        }

        private Sort handleSorting(String sortby, String direction, SeatClass seatClass) {
                if (sortby == null)
                        return null;

                if (direction == null)
                        direction = "asc";
                else
                        direction = direction.toLowerCase();

                if ("price".equals(sortby))
                        sortby = (seatClass == SeatClass.ECONOMY) ? "economyPrice" : "businessPrice";
                else
                        return null;
                return Sort.by(Sort.Direction.fromString(direction), sortby);
        }

}
