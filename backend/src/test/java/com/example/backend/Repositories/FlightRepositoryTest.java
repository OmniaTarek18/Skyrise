package com.example.backend.Repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.example.backend.Entities.Flight;

@DataJpaTest
@EntityScan(basePackages = "com.example.backend.Repositories.FlightRepository")

public class FlightRepositoryTest {

    @Autowired
    private FlightRepository flightRepository;

    @Test
    void testFindByDepartureDateCorrectence() {
        // given
        String[] source = { "New York", "Cairo", "Miami", "New York", "San Fransico", "Cairo" };
        String[] destination = { "Miami", "Aswan", "New York", "Miami", "Miami", "Alexandria" };
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

        List<Flight> actual = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            Flight flight = Flight.builder()
                    .source(source[i])
                    .destination(destination[i])
                    .departureDate(date[i])
                    .arrivalDate(arrivalDate)
                    .arrivalTime(arrivalTime)
                    .departureTime(departureTime)
                    .economyPrice(economyPrice)
                    .businessPrice(businessPrice)
                    .availableEconomySeats(availableEconomySeats)
                    .availableBusinessSeats(availableBusinessSeats)
                    .build();

            flightRepository.save(flight);

            if (i == 0 || i == 5)
                actual.add(flight);
        }

        // where
        Pageable pageable = PageRequest.of(0, 10);
        Page<Flight> page = flightRepository.findByDepartureDate(LocalDate.parse("2024-10-12"), pageable);

        // then
        Assertions.assertEquals(page.getContent(), actual);

    }

    @Test
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
}
