package com.example.backend.Services.AdminDashboard;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.backend.DTOs.PageResponse;
import com.example.backend.DTOs.AdminDashboard.AdminFlightDTO;
import com.example.backend.DTOs.AdminDashboard.FlightFilterCriteria;
import com.example.backend.Entities.Airport;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.FlightLeg;
import com.example.backend.Repositories.AirportRepository;
import com.example.backend.Repositories.FlightLegRepository;
import com.example.backend.Repositories.FlightRepository;

import jakarta.transaction.Transactional;
@SpringBootTest
@Transactional
public class ArchiveDisplayServiceIntegerationTest {
    @Autowired
    private ArchiveDisplayService archiveDisplayService;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private FlightLegRepository flightLegRepository;
    @Autowired
    private AirportRepository airportRepository;

    @BeforeEach
    void setup() {
        generateData();
    }

    @Test
    void testGetPastFlightsCorrectence() {
        // given
        int expected = 2;
        FlightFilterCriteria flightFilter = new FlightFilterCriteria(null, null, null, null, null, null);
        // where
        PageResponse<AdminFlightDTO> page = archiveDisplayService.filterPastFlights(flightFilter, 0);
        // then
        Assertions.assertEquals(expected, page.totalElements());
    }
    
    private Flight createFlight(LocalDate arrivalDate) {
        Flight flight = Flight.builder()
                .arrivalDate(arrivalDate)
                .departureDate(LocalDate.of(2024, 12, 5))
                .availableBusinessSeats(20)
                .availableEconomySeats(30)
                .businessPrice(200)
                .economyPrice(50)
                .build();
        flightRepository.save(flight);
        return flight;
    }

    private Airport createAirport(String name, String city, String country, String code) {
        Airport airport = Airport.builder()
                .airportName(name)
                .airportCity(city)
                .airportCountry(country)
                .airportCode(code)
                .build();
        airportRepository.save(airport);
        return airport;
    }

    private FlightLeg createFlightLeg(Airport arrival, Airport departure, LocalTime arrivalTime,
            LocalTime departureTime, Integer flightLegId, Flight flight) {
        FlightLeg flightLeg = FlightLeg.builder()
                .arrivalAirport(arrival)
                .departureAirport(departure)
                .arrivalTime(arrivalTime)
                .departureTime(departureTime)
                .flightLegId(flightLegId)
                .flight(flight)
                .flightId(flight.getId())
                .build();
        flightLegRepository.save(flightLeg);
        return flightLeg;

    }

    private void generateData() {
        Airport[] airports = new Airport[2];
        for (int i = 0; i < 2; i++)
            airports[i] = createAirport("Airport" + i, "City" + i, "Country" + i, "code" + i);

        Flight[] flights = new Flight[6];
        LocalDate[] arrivalDates = { LocalDate.now().minusDays(1), LocalDate.now().minusDays(3), LocalDate.now(),
                LocalDate.now().plusDays(4), LocalDate.now().plusDays(2), LocalDate.now().plusDays(3) };
        for (int i = 0; i < 6; i++) {
            flights[i] = createFlight(arrivalDates[i]);
            FlightLeg flightLeg = createFlightLeg(airports[1], airports[0], LocalTime.of(12, 30), LocalTime.of(8, 0), 1,
                    flights[i]);
            flights[i].setFlightLegs(new ArrayList<>(List.of(flightLeg)));
            flightRepository.save(flights[i]);
        }
    }
}
