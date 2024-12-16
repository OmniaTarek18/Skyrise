package com.example.backend.Repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.backend.Entities.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer>, JpaSpecificationExecutor<Flight> {
    Page<Flight> findByDepartureDate(LocalDate departureDate, Pageable pageable);
    Page<Flight> findByArrivalDateLessThan(LocalDate arrivalDate, Pageable pageable);

    long countByArrivalDateGreaterThanEqual(LocalDate date);
}
