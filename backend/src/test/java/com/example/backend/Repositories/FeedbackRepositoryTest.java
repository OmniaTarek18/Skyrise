package com.example.backend.Repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.backend.Entities.Feedback;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.User;
import com.example.backend.Enums.QualityRating;

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
        String lastName = "Osama";
        String[] firstName = { "Ahmed", "Ali", "Mona", "Samia", "Rawan", "Omnia", "Nouran", "Rana" };
        for (String name : firstName) {
            User user = User.builder()
                    .firstName(name)
                    .lastName(lastName)
                    .build();
            userRepository.save(user);
        }

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

    @Test
    void testGetAverageRatingFunctionality() {
        short[] stars = { 2, 3, 4, 5 };
        for (int i = 0 ; i < stars.length ; i++) {
            User user = userRepository.findAll().get(i);
            Flight savedFlight = flightRepository.findAll().get(0);
            Feedback feedback = Feedback.builder()
                    .user(user)
                    .flight(savedFlight)
                    .cleanliness(QualityRating.FAIR)
                    .comfort(QualityRating.EXCELLENT)
                    .service(QualityRating.EXCELLENT)
                    .punctuality(QualityRating.EXCELLENT)
                    .foodAndBeverage(QualityRating.EXCELLENT)
                    .dateOfCreation(LocalDateTime.now())
                    .stars(stars[i])
                    .build();
            feedbackRepository.save(feedback);
        }
        Double expected_avg = 0.0 ;
        for(short i : stars) expected_avg += i;
        expected_avg /= stars.length;
        Double actual_avg =  feedbackRepository.getAvgRating();
        Assertions.assertEquals(expected_avg, actual_avg);
    }

    @Test 
    void testGetAverageRatingWhenDatabaseIsEmpty(){
        Double expected_avg = 0.0 ;
        Double actual_avg =  feedbackRepository.getAvgRating();
        Assertions.assertEquals(expected_avg, actual_avg);
    }
}
