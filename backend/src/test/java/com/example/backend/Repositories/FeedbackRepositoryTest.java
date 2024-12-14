package com.example.backend.Repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.backend.Entities.Account;
import com.example.backend.Entities.Feedback;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.User;
import com.example.backend.Enums.Gender;
import com.example.backend.Enums.QualityRating;
import com.example.backend.Enums.Role;
import java.util.Random;

@DataJpaTest
public class FeedbackRepositoryTest {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;

    @BeforeEach
    void setup() {
        addOneUser();

        int numOfFlights = 10;
        addFlights(numOfFlights);
    }

    @Test
    void testGetAverageRatingWhenDatabaseIsEmpty() {
        // given
        Double expected_avg = 0.0;
        // when
        Double actual_avg = feedbackRepository.getAvgRating();
        // then
        Assertions.assertEquals(expected_avg, actual_avg);
    }

    @Test
    void testGetAverageRatingFunctionality() {
        // given
        short[] stars = { 2, 3, 4, 5 };
        for (int i = 0; i < stars.length; i++) {
            User user = userRepository.findAll().get(0);
            Flight savedFlight = flightRepository.findAll().get(i);
            addFeedback(user, savedFlight, stars[i]);
        }
        Double expected_avg = 0.0;
        for (short i : stars)
            expected_avg += i;
        expected_avg /= stars.length;
        // when
        Double actual_avg = feedbackRepository.getAvgRating();
        // then
        Assertions.assertEquals(expected_avg, actual_avg);
    }

    @Test
    void testGetAverageRatingOnLargeDatabase() {
        // given
        int numOfFlights = 1000;
        addFlights(numOfFlights);

        short[] stars = new short[numOfFlights];
        Random rd = new Random();
        for (int i = 0; i < stars.length; i++) {
            stars[i] = (short) rd.nextInt(6);
        }

        for (int i = 0; i < stars.length; i++) {
            User user = userRepository.findAll().get(0);
            Flight savedFlight = flightRepository.findAll().get(i);
            addFeedback(user, savedFlight, stars[i]);
        }
        Double expected_avg = 0.0;
        for (short i : stars)
            expected_avg += i;
        expected_avg /= stars.length;
        // when
        Double actual_avg = feedbackRepository.getAvgRating();
        // then
        Assertions.assertEquals(expected_avg, actual_avg);
    }

    private void addFlights(int numOfFlights) {
        for (int i = 0; i < numOfFlights; i++) {
            Flight flight = Flight.builder()
                    .arrivalDate(LocalDate.of(2024, 12, 5))
                    .departureDate(LocalDate.of(2024, 12, 5))
                    .availableBusinessSeats(20)
                    .availableEconomySeats(30)
                    .businessPrice(200)
                    .economyPrice(50)
                    .build();
            flightRepository.save(flight);
        }
    }

    private void addOneUser() {
        Account account = Account.builder()
                .accountId(1)
                .email("example@gmail.com")
                .password("password")
                .role(Role.USER)
                .build();

        User user = User.builder()
                .userId(1)
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
    }

    private void addFeedback(User user, Flight flight, short stars) {
        Feedback feedback = Feedback.builder()
                .user(user)
                .flight(flight)
                .cleanliness(QualityRating.FAIR)
                .comfort(QualityRating.EXCELLENT)
                .service(QualityRating.EXCELLENT)
                .punctuality(QualityRating.EXCELLENT)
                .foodAndBeverage(QualityRating.EXCELLENT)
                .dateOfCreation(LocalDateTime.now())
                .stars(stars)
                .build();
        feedbackRepository.save(feedback);
    }
}