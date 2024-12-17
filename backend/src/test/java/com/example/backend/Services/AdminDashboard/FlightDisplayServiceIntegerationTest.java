package com.example.backend.Services.AdminDashboard;

import java.time.LocalDate;
import java.time.LocalTime;

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

// Test the dynamic filtering 
@SpringBootTest
public class FlightDisplayServiceIntegerationTest {

    @Autowired
    private FlightDisplayService flightDisplayService;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private FlightLegRepository flightLegRepository;
    @Autowired
    private AirportRepository airportRepository;

    @BeforeEach
    void setup() {
        cleanDatabase();
        generateData();
    }

    @Test
    void testFilterFlightsCorrectenceUsingAllFeatures() {
        // given
        int expected_number = 2;
        LocalDate departueDate = LocalDate.of(2024, 10, 20);
        String source = "City2";
        String destination = "City5";
        String status = "complete";
        // when
        FlightFilterCriteria flightFilter = new FlightFilterCriteria(departueDate, source, destination, status, null,
                null);
        PageResponse<AdminFlightDTO> page = flightDisplayService.filterFlights(flightFilter, 0);
        // then
        Assertions.assertEquals(expected_number, page.totalElements());
        Assertions.assertEquals(0, page.content().get(0).availableBusinessSeats());
        Assertions.assertEquals(0, page.content().get(0).availableEconomySeats());
    }

    @Test
    void testFilterFlightsCorrectenceWhenDestinationAndStatusAreNull() {
        // given
        int expected_number = 4;
        LocalDate departueDate = LocalDate.of(2024, 10, 20);
        String source = "City2";
        String destination = null;
        String status = null;
        // when
        FlightFilterCriteria flightFilter = new FlightFilterCriteria(departueDate, source, destination, status, null,
                null);
        PageResponse<AdminFlightDTO> page = flightDisplayService.filterFlights(flightFilter, 0);
        // then
        Assertions.assertEquals(expected_number, page.totalElements());
        Assertions.assertEquals(source, page.content().get(0).source());
        Assertions.assertEquals(source, page.content().get(1).source());
        Assertions.assertEquals(source, page.content().get(2).source());
        Assertions.assertEquals(source, page.content().get(3).source());
    }

    @Test
    void testFilterFlightsCorrectenceWhenStatusIsNull() {
        // given
        int expected_number = 3;
        LocalDate departueDate = LocalDate.of(2024, 10, 20);
        String source = "City2";
        String destination = "City5";
        String status = null;
        // when
        FlightFilterCriteria flightFilter = new FlightFilterCriteria(departueDate, source, destination, status, null,
                null);
        PageResponse<AdminFlightDTO> page = flightDisplayService.filterFlights(flightFilter, 0);
        // then
        Assertions.assertEquals(expected_number, page.totalElements());
        Assertions.assertEquals(source, page.content().get(0).source());
        Assertions.assertEquals(source, page.content().get(1).source());
        Assertions.assertEquals(source, page.content().get(2).source());
        Assertions.assertEquals(destination, page.content().get(0).destination());
        Assertions.assertEquals(destination, page.content().get(1).destination());
        Assertions.assertEquals(destination, page.content().get(2).destination());
    }

    @Test
    void testFilterFlightsWhenAllAreNull() {
        // given
        int expected_number = 5;
        LocalDate departueDate = LocalDate.of(2024, 10, 20);
        String source = null;
        String destination = null;
        String status = null;
        // when
        FlightFilterCriteria flightFilter = new FlightFilterCriteria(departueDate, source, destination, status, null,
                null);
        PageResponse<AdminFlightDTO> page = flightDisplayService.filterFlights(flightFilter, 0);
        // then
        Assertions.assertEquals(expected_number, page.totalElements());
    }

    private void cleanDatabase() {
        flightRepository.deleteAll();
        flightLegRepository.deleteAll();
        airportRepository.deleteAll();
    }

    private Flight createFlights(LocalDate date, LocalDate arrivalDate, float economyPrice, float businessPrice,
            int availableEconomySeats, int availableBusinessSeats, boolean isCancel) {
        Flight flight = Flight.builder()
                .isCancel(isCancel)
                .departureDate(date)
                .arrivalDate(arrivalDate)
                .economyPrice(economyPrice)
                .businessPrice(businessPrice)
                .availableEconomySeats(availableEconomySeats)
                .availableBusinessSeats(availableBusinessSeats)
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
        // create 6 airports
        Airport[] airports = new Airport[7];
        for (int i = 1; i < 7; i++)
            airports[i] = createAirport("Airport" + i, "City" + i, "Country" + i, "code" + i);

        Flight[] flights = new Flight[6];
        int[] availableSeats = { 0, 0, 4, 10, 0, 0 };
        LocalDate departureDate = LocalDate.of(2024, 10, 20);
        LocalDate[] arrivalDates = { LocalDate.of(2024, 10, 18), LocalDate.of(2024, 10, 24), LocalDate.of(2024, 10, 22),
                LocalDate.of(2024, 10, 23), LocalDate.of(2024, 10, 29) };
        boolean[] isCancel = { false, true, false, true, true };
        // create 5 flights
        for (int i = 1; i < 6; i++)
            flights[i] = createFlights(departureDate, arrivalDates[i - 1], 1500, 2000,
                    availableSeats[i], availableSeats[6 - i], isCancel[i - 1]);

        // create flightlegs
        // 3 legs for flight 1 (source is City2 , destination is City5)
        for (int i = 2; i < 5; i++) {
            createFlightLeg(airports[i + 1], airports[i], LocalTime.of(12, 30), LocalTime.of(8, 0), i - 1, flights[1]);
        }
        // 2 legs for flight 2 (source is City2, destination is City4)
        for (int i = 2; i < 4; i++) {
            createFlightLeg(airports[i + 1], airports[i], LocalTime.of(12, 30), LocalTime.of(8, 0), i - 1, flights[2]);
        }
        // 2 legs for flight 3 (source is City1, destination is City3)
        for (int i = 1; i < 3; i++) {
            createFlightLeg(airports[i + 1], airports[i], LocalTime.of(12, 30), LocalTime.of(8, 0), i, flights[3]);
        }
        // 4 legs for flight 4 (source is City2, destination is City5)
        for (int i = 2; i < 5; i++) {
            createFlightLeg(airports[i + 1], airports[i], LocalTime.of(12, 30), LocalTime.of(8, 0), i - 1, flights[4]);
        }
        // 1 leg for flight 5 (source is City2, destination is City5)
        createFlightLeg(airports[5], airports[2], LocalTime.of(12, 30), LocalTime.of(8, 0), 1, flights[5]);

    }
}
