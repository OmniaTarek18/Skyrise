package com.example.backend.Services;

import com.example.backend.Entities.Airport;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.FlightLeg;
import com.example.backend.Repositories.FlightLegRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


public class FlightLegServicesTest {

    @Mock
    private FlightLegRepository flightLegRepository;

    @InjectMocks
    private FlightLegServices flightLegServices;

    private FlightLeg validFlightLeg;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Airport departureAirport = new Airport();
        departureAirport.setId(1);

        Airport arrivalAirport = new Airport();
        arrivalAirport.setId(2);

        Flight flight = new Flight();
        flight.setId(1);

        validFlightLeg = FlightLeg.builder()
                .flight(flight)
                .arrivalAirport(arrivalAirport)
                .departureAirport(departureAirport)
                .departureTime(LocalTime.of(16, 0))
                .arrivalTime(LocalTime.of(18, 30))
                .build();
    }

    @Test
    void testAddFlightLeg_NoConflict() {
        assertDoesNotThrow(() -> flightLegServices.addFlightLeg(validFlightLeg));
    }


}
