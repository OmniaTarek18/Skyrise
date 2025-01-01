package com.example.backend.Services.AdminDashboard;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.backend.DTOs.AdminDashboard.FlightLegUpdateDTO;
import com.example.backend.Entities.Airport;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.FlightLeg;
import com.example.backend.Repositories.AirportRepository;
import com.example.backend.Repositories.FlightLegRepository;
import com.example.backend.Repositories.FlightRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class FlightLegManagementServiceTest {

    @Autowired
    private FlightLegManagementService flightLegManagementService;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private FlightLegRepository flightLegRepository;

    @Autowired
    private FlightRepository flightRepository;

    private Flight flight;
    private FlightLeg flightLeg1;
    private FlightLeg flightLeg2;
    private Airport arrivalAirport;
    private Airport departureAirport;

    @BeforeEach
    void setup() {
        departureAirport = createAirport("Departure Airport", "City1", "Country1", "CODE1");
        arrivalAirport = createAirport("Arrival Airport", "City2", "Country2", "CODE2");

        flight = createFlight(LocalDate.now(), LocalDate.now().plusDays(1), false);
        flightLeg1 = createFlightLeg(1, departureAirport, arrivalAirport, LocalTime.of(10, 0), LocalTime.of(12, 0),
                flight);
        flightLeg2 = createFlightLeg(2, arrivalAirport, departureAirport, LocalTime.of(13, 0), LocalTime.of(15, 0),
                flight);
        flight.setFlightLegs(new ArrayList<>(List.of(flightLeg1, flightLeg2)));
        flightRepository.save(flight);
    }

    @Test
    void testUpdateFlightLegsSuccess() {
        // Arrange
        FlightLegUpdateDTO dto1 = new FlightLegUpdateDTO(
                flightLeg1.getFlightLegId(),
                LocalTime.of(11, 0),
                LocalTime.of(13, 0),
                departureAirport.getId(),
                arrivalAirport.getId());

        FlightLegUpdateDTO dto2 = new FlightLegUpdateDTO(
                flightLeg2.getFlightLegId(),
                LocalTime.of(14, 0),
                LocalTime.of(16, 0),
                arrivalAirport.getId(),
                departureAirport.getId());

        List<FlightLegUpdateDTO> flightLegUpdateDTOs = List.of(dto1, dto2);

        // Act
        boolean result = flightLegManagementService.updateFlightLegs(flightLegUpdateDTOs, flight.getId());

        // Assert
        Assertions.assertTrue(result);

        // Reload the flight legs from the repository
        FlightLeg updatedFlightLeg1 = flightRepository.findById(flight.getId()).get().getFlightLegs().get(0);
        FlightLeg updatedFlightLeg2 = flightRepository.findById(flight.getId()).get().getFlightLegs().get(1);

        // Verify updated values
        Assertions.assertEquals(dto1.departureTime(), updatedFlightLeg1.getDepartureTime());
        Assertions.assertEquals(dto1.arrivalTime(), updatedFlightLeg1.getArrivalTime());
        Assertions.assertEquals(departureAirport, updatedFlightLeg1.getDepartureAirport());
        Assertions.assertEquals(arrivalAirport, updatedFlightLeg1.getArrivalAirport());

        Assertions.assertEquals(dto2.departureTime(), updatedFlightLeg2.getDepartureTime());
        Assertions.assertEquals(dto2.arrivalTime(), updatedFlightLeg2.getArrivalTime());
        Assertions.assertEquals(arrivalAirport, updatedFlightLeg2.getDepartureAirport());
        Assertions.assertEquals(departureAirport, updatedFlightLeg2.getArrivalAirport());
    }

    @Test
    void testUpdateFlightLegsNoChange() {
        // Arrange
        FlightLegUpdateDTO dto1 = new FlightLegUpdateDTO(
                flightLeg1.getFlightLegId(),
                flightLeg1.getDepartureTime(),
                flightLeg1.getArrivalTime(),
                flightLeg1.getDepartureAirport().getId(),
                flightLeg1.getArrivalAirport().getId());

        FlightLegUpdateDTO dto2 = new FlightLegUpdateDTO(
                flightLeg2.getFlightLegId(),
                flightLeg2.getDepartureTime(),
                flightLeg2.getArrivalTime(),
                flightLeg2.getDepartureAirport().getId(),
                flightLeg2.getArrivalAirport().getId());

        List<FlightLegUpdateDTO> flightLegUpdateDTOs = List.of(dto1, dto2);

        // Act
        boolean result = flightLegManagementService.updateFlightLegs(flightLegUpdateDTOs, flight.getId());

        // Assert
        Assertions.assertFalse(result);
        Assertions.assertEquals(dto1.departureTime(), flightLeg1.getDepartureTime());
        Assertions.assertEquals(dto1.arrivalTime(), flightLeg1.getArrivalTime());
    }

    @Test
    void testUpdateFlightLegsFlightNotFound() {
        // Act & Assert
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
                () -> flightLegManagementService.updateFlightLegs(List.of(), 1));
        Assertions.assertEquals("Flight doesn't exist", exception.getMessage());
    }

    private Flight createFlight(LocalDate departureDate, LocalDate arrivalDate, boolean isCancel) {
        Flight flight1 = Flight.builder()
                .departureDate(departureDate)
                .arrivalDate(arrivalDate)
                .economyPrice(100)
                .businessPrice(200)
                .availableEconomySeats(100)
                .availableBusinessSeats(50)
                .isCancel(isCancel)
                .build();
        flightRepository.save(flight1);
        return flight1;
    }

    private Airport createAirport(String name, String city, String country, String code) {
        Airport airport = Airport.builder()
                .airportName(name)
                .airportCity(city)
                .airportCountry(country)
                .airportCode(code)
                .latitude(0.0)
                .longitude(0.0)
                .build();
        airportRepository.save(airport);
        return airport;
    }

    private FlightLeg createFlightLeg(Integer flightLegId, Airport departureAirport, Airport arrivalAirport,
            LocalTime departureTime, LocalTime arrivalTime, Flight flight) {
        FlightLeg flightLeg = FlightLeg.builder()
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .departureTime(departureTime)
                .arrivalTime(arrivalTime)
                .flightLegId(flightLegId)
                .flightId(flight.getId())
                .flight(flight)
                .build();
        flightLegRepository.save(flightLeg);
        return flightLeg;
    }

}
