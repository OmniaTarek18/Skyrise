package com.example.backend.Repositories;

import java.time.LocalDate;
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
        LocalDate arrivalDate = LocalDate.parse("2024-10-12");
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
                    .departureDate(date[i])
                    .arrivalDate(arrivalDate)
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
