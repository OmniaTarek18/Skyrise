package com.example.backend.Repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
=======

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
>>>>>>> feat-SCRUM-45-Admin-Dashboard-Frontend
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
<<<<<<< HEAD
import org.springframework.test.annotation.Rollback;

=======
>>>>>>> feat-SCRUM-45-Admin-Dashboard-Frontend
import com.example.backend.Entities.Flight;

@DataJpaTest
@EntityScan(basePackages = "com.example.backend.Repositories.FlightRepository")

public class FlightRepositoryTest {

    @Autowired
<<<<<<< HEAD
    private FlightRepository underTest;

    private final LocalDate DEPARTURE_DATE = LocalDate.parse("2024-10-12");

    @Test
    @Order(1)
    void testFindByDepartureDateWhenDatabaseIsEmpty() {
        // given
        int pageSize = 10;
        int pageNumber = 0;

        // where
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Flight> page = underTest.findByDepartureDate(DEPARTURE_DATE, pageable);

        // then
        Assertions.assertTrue(page.isEmpty());
    }

    @Test
    @Rollback(value = false)
    @Order(2)
=======
    private FlightRepository flightRepository;

    @Test
>>>>>>> feat-SCRUM-45-Admin-Dashboard-Frontend
    void testFindByDepartureDateCorrectence() {
        // given
        LocalDate arrivalDate = LocalDate.parse("2024-10-12");
        int availableBusinessSeats = 10;
        int availableEconomySeats = 50;
        float economyPrice = 200;
        float businessPrice = 2000;

        LocalDate[] date = {
                LocalDate.parse("2024-10-12"), LocalDate.parse("2024-09-12"),
                LocalDate.parse("2024-08-12"), LocalDate.parse("2024-10-11"),
                LocalDate.parse("2025-10-12"), LocalDate.parse("2024-10-12") };

<<<<<<< HEAD
        List<Flight> expected = new ArrayList<>();

        int pageSize = 10;
        int pageNumber = 0;

        for (int i = 0; i < date.length; i++) {
=======
        List<Flight> actual = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
>>>>>>> feat-SCRUM-45-Admin-Dashboard-Frontend
            Flight flight = Flight.builder()
                    .departureDate(date[i])
                    .arrivalDate(arrivalDate)
                    .economyPrice(economyPrice)
                    .businessPrice(businessPrice)
                    .availableEconomySeats(availableEconomySeats)
                    .availableBusinessSeats(availableBusinessSeats)
                    .build();

<<<<<<< HEAD
            underTest.save(flight);

            if (DEPARTURE_DATE.equals(date[i]))
                expected.add(flight);
        }

        // where
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Flight> page = underTest.findByDepartureDate(LocalDate.parse("2024-10-12"), pageable);

        // then
        Assertions.assertEquals(expected, page.getContent());
        Assertions.assertEquals(2, page.getNumberOfElements());
=======
            flightRepository.save(flight);

            if (i == 0 || i == 5)
                actual.add(flight);
        }

        // where
        Pageable pageable = PageRequest.of(0, 10);
        Page<Flight> page = flightRepository.findByDepartureDate(LocalDate.parse("2024-10-12"), pageable);

        // then
        Assertions.assertEquals(page.getContent(), actual);
>>>>>>> feat-SCRUM-45-Admin-Dashboard-Frontend

    }

    @Test
<<<<<<< HEAD
    @Order(3)
    void testFindByDepartureDateWhenPageIsNotFound() {
        // given
        int pageSize = 10;
        int pageNumber = 1;

        // where
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Flight> page = underTest.findByDepartureDate(DEPARTURE_DATE, pageable);

        // then
        Assertions.assertTrue(page.isEmpty());
    }

=======
    void testFindByDepartureDateWhenNotFound() {
        // given
        List<Flight> actual = new ArrayList<>();

        // where
        Pageable pageable = PageRequest.of(0, 10);
        Page<Flight> page = flightRepository.findByDepartureDate(LocalDate.parse("2024-10-12"), pageable);

        // then
        Assertions.assertEquals(page.getContent(), actual);
    }

    @Test
    void testFindByDepartureDateWhen() {
        // given
        List<Flight> actual = new ArrayList<>();

        // where
        Pageable pageable = PageRequest.of(0, 10);
        Page<Flight> page = flightRepository.findByDepartureDate(LocalDate.parse("2024-10-12"), pageable);

        // then
        Assertions.assertEquals(page.getContent(), actual);
    }
    // if search for page that not exist
    // if the departure date was null or not date
>>>>>>> feat-SCRUM-45-Admin-Dashboard-Frontend
}
