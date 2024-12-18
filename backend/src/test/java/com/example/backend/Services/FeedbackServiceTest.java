package com.example.backend.Services;

import com.example.backend.DTOs.FeedbackDTO;
import com.example.backend.Entities.Account;
import com.example.backend.Entities.Flight;
import com.example.backend.Enums.QualityRating;
import com.example.backend.Enums.Role;
import com.example.backend.Repositories.FeedbackRepository;
import com.example.backend.Repositories.FlightRepository;
import com.example.backend.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
public class FeedbackServiceTest {

    @Autowired
    private FeedbackServices feedbackServices;
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void addFeedback() {
        FeedbackDTO feedbackDTO = new FeedbackDTO(1, 1, (short) 4, "Good",
                "Ahmed", "Dubai", "2015-1-2", LocalDate.of(2016, 1, 1),
                QualityRating.EXCELLENT, QualityRating.EXCELLENT, QualityRating.EXCELLENT, QualityRating.EXCELLENT,
                QualityRating.EXCELLENT, LocalDateTime.of(2024, 12, 18, 12, 30, 0, 0)
        );

        try{
            feedbackServices.addFeedback(feedbackDTO);
        }
        catch (Exception e){
            Assertions.assertEquals(e.getMessage(), "There is no flight with that id");
        }

    }
}
