package com.example.backend.Repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import com.example.backend.Entities.Flight;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FlightRepositoryTest {

    @Autowired
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
    void testFindByDepartureDateCorrectence() {
        // given
        String source = "New York";
        String destination = "Miami";
        LocalDate arrivalDate = LocalDate.parse("2024-10-12");
        LocalTime arrivalTime = LocalTime.parse("10:12:00");
        LocalTime departureTime = LocalTime.parse("10:12:00");
        int availableBusinessSeats = 10;
        int availableEconomySeats = 50;
        float economyPrice = 200;
        float businessPrice = 2000;

        LocalDate[] date = {
                LocalDate.parse("2024-10-12"), LocalDate.parse("2024-09-12"),
                LocalDate.parse("2024-08-12"), LocalDate.parse("2024-10-11"),
                LocalDate.parse("2025-10-12"), LocalDate.parse("2024-10-12") };

        List<Flight> expected = new ArrayList<>();

        int pageSize = 10;
        int pageNumber = 0;

        for (int i = 0; i < 6; i++) {
            Flight flight = Flight.builder()
                    .source(source)
                    .destination(destination)
                    .departureDate(date[i])
                    .arrivalDate(arrivalDate)
                    .arrivalTime(arrivalTime)
                    .departureTime(departureTime)
                    .economyPrice(economyPrice)
                    .businessPrice(businessPrice)
                    .availableEconomySeats(availableEconomySeats)
                    .availableBusinessSeats(availableBusinessSeats)
                    .build();

            underTest.save(flight);

            if (i == 0 || i == 5)
                expected.add(flight);
        }

        // where
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Flight> page = underTest.findByDepartureDate(LocalDate.parse("2024-10-12"), pageable);

        // then
        Assertions.assertEquals(expected, page.getContent());
        Assertions.assertEquals(2, page.getNumberOfElements());

    }

    @Test
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

}