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
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.DTOs.AdminDashboard.FlightUpdateDTO;
import com.example.backend.Entities.Account;
import com.example.backend.Entities.Airport;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.FlightLeg;
import com.example.backend.Entities.Reservation;
import com.example.backend.Entities.User;
import com.example.backend.Enums.Gender;
import com.example.backend.Enums.Role;
import com.example.backend.Enums.SeatClass;
import com.example.backend.Repositories.AccountRepository;
import com.example.backend.Repositories.AirportRepository;
import com.example.backend.Repositories.FlightLegRepository;
import com.example.backend.Repositories.FlightRepository;
import com.example.backend.Repositories.ReservationRepository;
import com.example.backend.Repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
@Transactional
public class FlightManagementServiceTest {

    @Autowired
    private FlightManagmentService flightManagementService;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightLegRepository flightLegRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    private Flight testFlight;
    private FlightLeg testFlightLeg;

    @BeforeEach
    void setup() {
        Airport departureAirport = createAirport("Departure Airport", "City1", "Country1", "CODE1");
        Airport arrivalAirport = createAirport("Arrival Airport", "City2", "Country2", "CODE2");

        testFlight = createFlight(
                LocalDate.of(2024, 10, 15),
                LocalDate.of(2024, 10, 16),
                false
        );

        testFlightLeg = createFlightLeg(1,
                departureAirport,
                arrivalAirport,
                LocalTime.of(10, 0),
                LocalTime.of(14, 0),
                testFlight
        );

        testFlight.setFlightLegs(new ArrayList<>(List.of(testFlightLeg)));
        flightRepository.save(testFlight);
    }

    @Test
    void testDeleteFlight_Success() {
        // Act
        flightManagementService.deleteFlight(testFlight.getId());

        // Assert
        Assertions.assertFalse(flightRepository.existsById(testFlight.getId()));
    }

    @Test
    void testDeleteFlight_FlightDoesNotExist() {
        // Assert
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            flightManagementService.deleteFlight(9999); // Non-existent flight ID
        });
        assert exception.getMessage().equals("Flight doesn't exist");
    }

    @Test
    void testCancelFlight_Success() {
        //Arrange
        User user = createUser(createAccount());
        Reservation reservation = createReservation(user, testFlight);
        testFlight.setReservations(new ArrayList<>(List.of(reservation)));
        flightRepository.save(testFlight);

        // Act
        flightManagementService.cancelFlight(testFlight.getId());

        // Assert
        Flight updatedFlight = flightRepository.findById(testFlight.getId()).orElseThrow();
        Assertions.assertTrue(updatedFlight.isCancel());
    }

    @Test
    void testCancelFlight_FlightDoesNotExist() {
        // Assert
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            flightManagementService.cancelFlight(9999); // Non-existent flight ID
        });
        assert exception.getMessage().equals("Flight doesn't exist");
    }

    @Test
    void testUpdateFlight_Success() {
        // Arrange
        User user = createUser(createAccount());
        Reservation reservation = createReservation(user, testFlight);
        testFlight.setReservations(new ArrayList<>(List.of(reservation)));
        flightRepository.save(testFlight);

        FlightUpdateDTO flightUpdateDTO = new FlightUpdateDTO(
                testFlight.getId(),
                LocalDate.of(2024, 10, 17),
                LocalDate.of(2024, 10, 18),
                List.of()
        );

        // Act
        flightManagementService.updateFlight(flightUpdateDTO);

        // Assert
        Flight updatedFlight = flightRepository.findById(testFlight.getId()).orElseThrow();
        Assertions.assertEquals(LocalDate.of(2024, 10, 18), updatedFlight.getDepartureDate());
        Assertions.assertEquals(LocalDate.of(2024, 10, 17), updatedFlight.getArrivalDate());
    }

    @Test
    void testUpdateFlight_NoChanges() {
        // Arrange
        User user = createUser(createAccount());
        Reservation reservation = createReservation(user, testFlight);
        testFlight.setReservations(new ArrayList<>(List.of(reservation)));
        flightRepository.save(testFlight);

        FlightUpdateDTO flightUpdateDTO = new FlightUpdateDTO(
                testFlight.getId(),
                testFlight.getArrivalDate(),
                testFlight.getDepartureDate(),
                List.of()
        );

        // Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            flightManagementService.updateFlight(flightUpdateDTO);
        });
        assert exception.getMessage().equals("No changes made");
    }

    private Flight createFlight(LocalDate departureDate, LocalDate arrivalDate, boolean isCancel) {
        Flight flight = Flight.builder()
                .departureDate(departureDate)
                .arrivalDate(arrivalDate)
                .economyPrice(100)
                .businessPrice(200)
                .availableEconomySeats(100)
                .availableBusinessSeats(50)
                .isCancel(isCancel)
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
                .latitude(0.0)
                .longitude(0.0)
                .build();
        airportRepository.save(airport);
        return airport;
    }

    private FlightLeg createFlightLeg(Integer flightLegId, Airport departureAirport, Airport arrivalAirport, LocalTime departureTime, LocalTime arrivalTime, Flight flight) {
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

    private User createUser(Account account) {
        User user = User.builder()
                .account(account)
                .gender(Gender.FEMALE)
                .firstName("firstName")
                .lastName("lastName")
                .nationalId("nationalId")
                .dateOfBirth(LocalDate.of(2024, 12, 10))
                .countryCode("CountryCode")
                .phoneNumber("phoneNumber")
                .passportNumber("PassportNumber")
                .passportIssuingCountry("passportIssuingCountry")
                .build();
        userRepository.save(user);
        return user;
    }

    private Account createAccount() {
        Account account = Account.builder()
                .email("example@gmail.com")
                .password("password")
                .role(Role.USER)
                .build();
        accountRepository.save(account);
        return account;
    }

    private Reservation createReservation(User user, Flight flight) {
        Reservation reservation = Reservation.builder()
                .user(user)
                .flight(flight)
                .seatClass(SeatClass.ECONOMY)
                .reservedSeats(2)
                .build();
        reservationRepository.save(reservation);
        return reservation;
    }
}
