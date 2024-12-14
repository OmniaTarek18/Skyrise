package com.example.backend.Specifications;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;

import com.example.backend.Entities.Account;
import com.example.backend.Entities.Feedback;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.User;
import com.example.backend.Enums.Gender;
import com.example.backend.Enums.QualityRating;
import com.example.backend.Enums.Role;
import com.example.backend.Repositories.AccountRepository;
import com.example.backend.Repositories.FeedbackRepository;
import com.example.backend.Repositories.FlightRepository;
import com.example.backend.Repositories.UserRepository;

@DataJpaTest
public class FeedbackSpecificationsTest {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    void setup() {
        generateData();
    }

    @Test
    void testContainsCleanliness() {
        // given
        int expected_number_of_feedback_excellent = 2;
        int expected_number_of_feedback_poor = 1;
        // when
        Specification<Feedback> spec1 = FeedbackSpecifications.containsCleanliness(QualityRating.EXCELLENT);
        List<Feedback> list_execllent = feedbackRepository.findAll(spec1);
        Specification<Feedback> spec2 = FeedbackSpecifications.containsCleanliness(QualityRating.POOR);
        List<Feedback> list_poor = feedbackRepository.findAll(spec2);
        // then
        Assertions.assertEquals(expected_number_of_feedback_excellent, list_execllent.size());
        Assertions.assertEquals(expected_number_of_feedback_poor, list_poor.size());
    }

    @Test
    void testContainsComfort() {
        // given
        int expected_number_of_feedback_excellent = 2;
        int expected_number_of_feedback_poor = 1;
        // when
        Specification<Feedback> spec1 = FeedbackSpecifications.containsCleanliness(QualityRating.EXCELLENT);
        List<Feedback> list_execllent = feedbackRepository.findAll(spec1);
        Specification<Feedback> spec2 = FeedbackSpecifications.containsCleanliness(QualityRating.POOR);
        List<Feedback> list_poor = feedbackRepository.findAll(spec2);
        // then
        Assertions.assertEquals(expected_number_of_feedback_excellent, list_execllent.size());
        Assertions.assertEquals(expected_number_of_feedback_poor, list_poor.size());
    }

    @Test
    void testContainsFoodAndBeverage() {
        // given
        int expected_number_of_feedback_excellent = 2;
        int expected_number_of_feedback_fair = 3;
        // when
        Specification<Feedback> spec1 = FeedbackSpecifications.containsFoodAndBeverage(QualityRating.EXCELLENT);
        List<Feedback> list_execllent = feedbackRepository.findAll(spec1);
        Specification<Feedback> spec2 = FeedbackSpecifications.containsFoodAndBeverage(QualityRating.FAIR);
        List<Feedback> list_fair = feedbackRepository.findAll(spec2);
        // then
        Assertions.assertEquals(expected_number_of_feedback_excellent, list_execllent.size());
        Assertions.assertEquals(expected_number_of_feedback_fair, list_fair.size());
    }

    @Test
    void testContainsPunctuality() {
        // given
        int expected_number_of_feedback_excellent = 0;
        int expected_number_of_feedback_poor = 4;
        // when
        Specification<Feedback> spec1 = FeedbackSpecifications.containsPunctuality(QualityRating.EXCELLENT);
        List<Feedback> list_execllent = feedbackRepository.findAll(spec1);
        Specification<Feedback> spec2 = FeedbackSpecifications.containsPunctuality(QualityRating.POOR);
        List<Feedback> list_poor = feedbackRepository.findAll(spec2);
        // then
        Assertions.assertEquals(expected_number_of_feedback_excellent, list_execllent.size());
        Assertions.assertEquals(expected_number_of_feedback_poor, list_poor.size());
    }

    @Test
    void testContainsService() {
        // given
        int expected_number_of_feedback_excellent = 2;
        int expected_number_of_feedback_poor = 3;
        // when
        Specification<Feedback> spec1 = FeedbackSpecifications.containsService(QualityRating.EXCELLENT);
        List<Feedback> list_execllent = feedbackRepository.findAll(spec1);
        Specification<Feedback> spec2 = FeedbackSpecifications.containsService(QualityRating.POOR);
        List<Feedback> list_poor = feedbackRepository.findAll(spec2);
        // then
        Assertions.assertEquals(expected_number_of_feedback_excellent, list_execllent.size());
        Assertions.assertEquals(expected_number_of_feedback_poor, list_poor.size());
    }

    @Test
    void testContainsStars() {
        // given
        int expected_number_of_feedback_2 = 2;
        int expected_number_of_feedback_5 = 0;
        // when
        Specification<Feedback> spec1 = FeedbackSpecifications.containsStars((short) 2);
        List<Feedback> list_execllent = feedbackRepository.findAll(spec1);
        Specification<Feedback> spec2 = FeedbackSpecifications.containsStars((short) 5);
        List<Feedback> list_poor = feedbackRepository.findAll(spec2);
        // then
        Assertions.assertEquals(expected_number_of_feedback_2, list_execllent.size());
        Assertions.assertEquals(expected_number_of_feedback_5, list_poor.size());
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

    private Flight createFlight() {
        Flight flight = Flight.builder()
                .arrivalDate(LocalDate.of(2024, 12, 5))
                .departureDate(LocalDate.of(2024, 12, 5))
                .availableBusinessSeats(20)
                .availableEconomySeats(30)
                .businessPrice(200)
                .economyPrice(50)
                .build();
        flightRepository.save(flight);
        return flight;
    }

    private Feedback createFeedback(User user, Flight flight, short stars, QualityRating service,
            QualityRating cleanliness, QualityRating comfort, QualityRating punctuality,
            QualityRating foodAndBeverage) {
        Feedback feedback = Feedback.builder()
                .user(user)
                .flight(flight)
                .cleanliness(cleanliness)
                .comfort(comfort)
                .service(service)
                .punctuality(punctuality)
                .foodAndBeverage(foodAndBeverage)
                .dateOfCreation(LocalDateTime.now())
                .stars(stars)
                .build();
        feedbackRepository.save(feedback);
        return feedback;
    }

    private void generateData() {
        User user = createUser(createAccount());

        Flight[] flights = new Flight[6];
        for (int i = 0; i < 6; i++)
            flights[i] = createFlight();

        Feedback[] feedback = new Feedback[6];
        QualityRating[] cleanliness = { QualityRating.EXCELLENT, QualityRating.FAIR, QualityRating.POOR,
                QualityRating.FAIR, QualityRating.EXCELLENT, QualityRating.FAIR };
        QualityRating[] comfort = { QualityRating.FAIR, QualityRating.POOR, QualityRating.EXCELLENT, QualityRating.POOR,
                QualityRating.EXCELLENT, QualityRating.FAIR };
        QualityRating[] service = { QualityRating.EXCELLENT, QualityRating.POOR, QualityRating.FAIR,
                QualityRating.EXCELLENT, QualityRating.POOR, QualityRating.POOR };
        QualityRating[] punctuality = { QualityRating.POOR, QualityRating.POOR, QualityRating.POOR,
                QualityRating.FAIR, QualityRating.FAIR, QualityRating.POOR };
        QualityRating[] foodAndBeverage = { QualityRating.EXCELLENT, QualityRating.FAIR, QualityRating.POOR,
                QualityRating.FAIR, QualityRating.EXCELLENT, QualityRating.FAIR };
        short[] stars = { 1, 2, 2, 1, 3, 4 };
        for (int i = 0; i < 6; i++)
            feedback[i] = createFeedback(user, flights[i], stars[i], service[i], cleanliness[i], comfort[i],
                    punctuality[i], foodAndBeverage[i]);
    }
}
