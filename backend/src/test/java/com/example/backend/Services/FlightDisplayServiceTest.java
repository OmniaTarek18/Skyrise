package com.example.backend.Services;

import static org.mockito.Mockito.verifyNoInteractions;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;

import com.example.backend.DTOMappers.AdminFlightMapper;
import com.example.backend.DTOs.AdminFlightDTO;
import com.example.backend.DTOs.PageResponse;
import com.example.backend.Entities.Flight;
import com.example.backend.Repositories.FlightRepository;

@ExtendWith(MockitoExtension.class)
public class FlightDisplayServiceTest {

    @Mock
    private FlightRepository flightRepository;
    @Mock
    private AdminFlightMapper adminFlightMapper;

    @InjectMocks
    private FlightDisplayService flightService;

    private final LocalDate DEPARTURE_DATE = LocalDate.parse("2024-12-02");
    
    @BeforeAll
    @Rollback(value = false)
    public void setup(){
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

        List<Flight> expected = new ArrayList<>();

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

            if (i == 0 || i == 5)
                expected.add(flight);
        }

    }

    @Test
    void testGetFlightsWhenDepartureDateIsNull() {
        // given
        LocalDate deparuterDate = null;
        // when
        Exception exception = Assertions.assertThrows(NullPointerException.class,
                () -> flightService.getFlights(deparuterDate, 0));
        // then
        Assertions.assertEquals("Departure date can't be null", exception.getMessage());
        verifyNoInteractions(flightRepository);
    }

    @Test
    void testGetFlightsWhenPageNumberIsNegative() {
        // given
        int pageNumber = -5;

        // when
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> flightService.getFlights(DEPARTURE_DATE, pageNumber));

        // then
        Assertions.assertEquals("Page number can't be negative", exception.getMessage());
        verifyNoInteractions(flightRepository);
    }

    @Test
    void testGetFlightsCorrectence() {
        // given
         
    }

    @Test
    void testFilterFlights() {
        // given (input)
        // when (test)
        // then (compare with expected)
    }

    // test if pages or invaild
    // test if any parameter is invaild
    //
}
