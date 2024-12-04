package com.example.backend.Services;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.backend.DTOMappers.AdminFlightMapper;
import com.example.backend.Repositories.FlightRepository;

@ExtendWith(MockitoExtension.class)
public class FlightDisplayServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private AdminFlightMapper adminFlightMapper;

    @InjectMocks
    private FlightDisplayService flightService;

    private final LocalDate DEPARTURE_DATE = LocalDate.parse("2024-10-12");

    @Test
    void testGetFlightsWhenDepartureDateIsNull() {
        LocalDate departureDate = null;

        Exception exception = Assertions.assertThrows(NullPointerException.class,
                () -> flightService.getFlights(departureDate, 0));

        Assertions.assertEquals("Departure date can't be null", exception.getMessage());
        verifyNoInteractions(flightRepository);
    }

    @Test
    void testGetFlightsWhenPageNumberIsNegative() {
        int pageNumber = -5;

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> flightService.getFlights(DEPARTURE_DATE, pageNumber));

        Assertions.assertEquals("Page number can't be negative", exception.getMessage());
        verifyNoInteractions(flightRepository);
    }
}