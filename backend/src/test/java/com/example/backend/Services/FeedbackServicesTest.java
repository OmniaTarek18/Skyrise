package com.example.backend.Services;

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
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.DTOs.FeedbackDTO;
import com.example.backend.Entities.Account;
import com.example.backend.Entities.Airport;
import com.example.backend.Entities.Feedback;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.FlightLeg;
import com.example.backend.Entities.User;
import com.example.backend.Enums.Gender;
import com.example.backend.Enums.QualityRating;
import com.example.backend.Enums.Role;
import com.example.backend.Repositories.AccountRepository;
import com.example.backend.Repositories.AirportRepository;
import com.example.backend.Repositories.FeedbackRepository;
import com.example.backend.Repositories.FlightLegRepository;
import com.example.backend.Repositories.FlightRepository;
import com.example.backend.Repositories.UserRepository;

@SpringBootTest
@Transactional
public class FeedbackServicesTest {

    @Autowired
    private FeedbackServices feedbackServices;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightLegRepository flightLegRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AirportRepository airportRepository;

    private User user;
    private Flight flight;

    @BeforeEach
    void setup() {
        generateData();
    }

    @Test
    void testAddFeedbackSuccess() {
        // Arrange
        short stars = 2;
        FeedbackDTO feedbackDTO = new FeedbackDTO(
                user.getUserId(),
                flight.getId(),
                stars,
                "Bad flight",
                user.getFirstName(),
                flight.getFlightLegs().get(0).getDepartureAirport().getAirportCity(),
                flight.getFlightLegs().get(0).getArrivalAirport().getAirportCity(),
                flight.getDepartureDate(),
                QualityRating.EXCELLENT,
                QualityRating.EXCELLENT,
                QualityRating.EXCELLENT,
                QualityRating.EXCELLENT,
                QualityRating.EXCELLENT,
                LocalDateTime.now());

        // Act
        boolean result = feedbackServices.addFeedback(feedbackDTO);

        // Assert
        Assertions.assertTrue(result);

        // Check that the feedback was saved correctly in the database
        Feedback feedback = feedbackRepository.findAll().get(0);
        Assertions.assertEquals(feedbackDTO.flightId(), feedback.getFlightId());
        Assertions.assertEquals(feedbackDTO.userId(), feedback.getUserId());
        Assertions.assertEquals(feedbackDTO.comment(), feedback.getComment());
        Assertions.assertEquals(feedbackDTO.stars(), feedback.getStars());
    }

    @Test
    void testAddFeedbackNoFlight() {
        // Arrange
        short stars = 2;
        FeedbackDTO feedbackDTO = new FeedbackDTO(
                user.getUserId(),
                9999,
                stars,
                "Bad flight",
                user.getFirstName(),
                flight.getFlightLegs().get(0).getDepartureAirport().getAirportCity(),
                flight.getFlightLegs().get(0).getArrivalAirport().getAirportCity(),
                flight.getDepartureDate(),
                QualityRating.EXCELLENT,
                QualityRating.EXCELLENT,
                QualityRating.EXCELLENT,
                QualityRating.EXCELLENT,
                QualityRating.EXCELLENT,
                LocalDateTime.now());

        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            feedbackServices.addFeedback(feedbackDTO);
        });
        Assertions.assertEquals("There is no flight with that id", exception.getMessage());
    }

    @Test
    void testAddFeedbackNoUser() {
        // Arrange
        short stars = 2;
        FeedbackDTO feedbackDTO = new FeedbackDTO(
                999,
                flight.getId(),
                stars,
                "Bad flight",
                user.getFirstName(),
                flight.getFlightLegs().get(0).getDepartureAirport().getAirportCity(),
                flight.getFlightLegs().get(0).getArrivalAirport().getAirportCity(),
                flight.getDepartureDate(),
                QualityRating.EXCELLENT,
                QualityRating.EXCELLENT,
                QualityRating.EXCELLENT,
                QualityRating.EXCELLENT,
                QualityRating.EXCELLENT,
                LocalDateTime.now());

        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            feedbackServices.addFeedback(feedbackDTO);
        });
        Assertions.assertEquals("There is no user with that id", exception.getMessage());
    }

    private void generateData() {
        // Create a user
        user = createUser(createAccount());

        // Create a flight and save it
        Airport departureAirport = createAirport("DepartureAirport", "City1", "Country1", "DPA");
        Airport arrivalAirport = createAirport("ArrivalAirport", "City2", "Country2", "APA");

        flight = createFlight(LocalDate.of(2024, 12, 10), LocalDate.of(2024, 12, 11), 100, 200, 100, 100, false);

        FlightLeg flightLeg = createFlightLeg(arrivalAirport, departureAirport, LocalTime.of(10, 0), LocalTime.of(12, 0), 1, flight);

        flight.setFlightLegs(new ArrayList<>(List.of(flightLeg)));

        flightRepository.save(flight);
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

    private Flight createFlight(LocalDate date, LocalDate arrivalDate, float economyPrice, float businessPrice,
            int availableEconomySeats, int availableBusinessSeats, boolean isCancel) {
        Flight flight1 = Flight.builder()
                .isCancel(isCancel)
                .departureDate(date)
                .arrivalDate(arrivalDate)
                .economyPrice(economyPrice)
                .businessPrice(businessPrice)
                .availableEconomySeats(availableEconomySeats)
                .availableBusinessSeats(availableBusinessSeats)
                .build();
        flightRepository.save(flight1);
        return flight1;
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

    private User createUser(Account account) {
        User user1 = User.builder()
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
        userRepository.save(user1);
        return user1;
    }

    private Account createAccount() {
        Account account = Account.builder()
                .email("example@gmail.com")
                .password("password")
                .role(Role.USER)
                .build();
        return accountRepository.save(account);
    }
}
