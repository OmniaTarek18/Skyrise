package com.example.backend.Services;

import com.example.backend.Entities.Flight;
import com.example.backend.Repositories.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class FlightServicesTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightServices flightService;

    private Flight validFlight;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        validFlight = Flight.builder()
                .arrivalDate(LocalDate.of(2024, 12, 20))
                .departureDate(LocalDate.of(2024, 12, 18))
                .economyPrice(200)
                .businessPrice(500)
                .availableEconomySeats(100)
                .availableBusinessSeats(20)
                .isCancel(false)
                .build();
    }


    @Test
    void testAddFlight_Success() {
        // Mock repository behavior
        when(flightRepository.save(any(Flight.class))).thenReturn(validFlight);

        // Perform the action
        int savedFlight = flightService.addFlight(validFlight);

        assertNotEquals(0, savedFlight);
    }


}
