package com.example.backend.Repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.backend.Entities.Airport;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.FlightLeg;

@DataJpaTest
public class FlightLegRepoTest {

    @Autowired
    private FlightLegRepository flightLegRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Test
    @DisplayName("Test 1: Find flight legs by flight id returns correct flight legs")
    @Rollback(true)
    void testFindByFlightIdCorrectness () {
        
        for (int i=0; i<3; i++) {
                Flight flight = Flight.builder()
                        .departureDate(LocalDate.of(2024, 12, 1))
                        .arrivalDate(LocalDate.of(2024, 12, 1))
                        .economyPrice(100.0f)
                        .businessPrice(200.0f)
                        .availableEconomySeats(100)
                        .availableBusinessSeats(50)
                        .isCancel(false)
                        .build();
                flightRepository.save(flight);
        }
        

        Airport departureAirport = Airport.builder()
                .airportName("JFK International Airport")
                .airportCountry("USA")
                .airportCity("New York")
                .airportCode("JFK")
                .latitude(40.6413)
                .longitude(-73.7781)
                .build();
        airportRepository.save(departureAirport);

        Airport arrivalAirport = Airport.builder()
                .airportName("LAX International Airport")
                .airportCountry("USA")
                .airportCity("Los Angeles")
                .airportCode("LAX")
                .latitude(33.9416)
                .longitude(-118.4085)
                .build();
        airportRepository.save(arrivalAirport);

        List<FlightLeg> actual = new ArrayList<>();

        for (int i=1; i<4; i++) {
                FlightLeg flightLeg = FlightLeg.builder()
                        .flightLegId(i)
                        .flightId(1)
                        .departureAirport(departureAirport)
                        .arrivalAirport(arrivalAirport)
                        .departureTime(LocalTime.of(10, 30))
                        .arrivalTime(LocalTime.of(12, 30))
                        .build();
                flightLegRepository.save(flightLeg);
                actual.add(flightLeg);
        }

        for (int i=2; i<4; i++) {
                FlightLeg flightLeg = FlightLeg.builder()
                        .flightLegId(1)
                        .flightId(i)
                        .departureAirport(departureAirport)
                        .arrivalAirport(arrivalAirport)
                        .departureTime(LocalTime.of(10, 30))
                        .arrivalTime(LocalTime.of(12, 30))
                        .build();
                flightLegRepository.save(flightLeg);
        }


        List<FlightLeg> result = flightLegRepository.findByFlightId(1);

        assertNotNull(result);
        assertEquals(3, result.size());
        assertThat(result).usingRecursiveFieldByFieldElementComparator().containsExactlyElementsOf(actual);
    }

    @Test
    @DisplayName("Test 2: Find flight legs by flight id returns empty list")
    @Rollback(true)
    void testFindByFlightIdReturnsEmpty() {
        List<FlightLeg> result = flightLegRepository.findByFlightId(999);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    
}
