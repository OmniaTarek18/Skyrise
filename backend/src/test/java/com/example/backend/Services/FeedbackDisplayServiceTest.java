package com.example.backend.Services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.data.domain.Pageable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.backend.DTOs.FeedbackDTO;
import com.example.backend.DTOs.PageResponse;
import com.example.backend.Entities.Feedback;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.User;
import com.example.backend.Enums.QualityRating;
import com.example.backend.Repositories.FeedbackRepository;

@ExtendWith(MockitoExtension.class)
public class FeedbackDisplayServiceTest {

    @InjectMocks
    private FeedbackDisplayService feedbackDisplayService;

    @Mock
    private FeedbackRepository feedbackRepository;

    // @Test
    // void testGetAll() {
    //     // given
    //     int pageNumber = 0;
    //     Pageable pageable = PageRequest.of(pageNumber, 10);
    //     List<Feedback> feedbackList = createFeedback();
    //     Page<Feedback> feedbackPage = new PageImpl<>(feedbackList, pageable, feedbackList.size());

    //     // mock the calls
    //     Mockito.when(feedbackRepository.findAll(pageable)).thenReturn(feedbackPage);
    //     // where
    //     PageResponse<FeedbackDTO> actualResult = feedbackDisplayService.getAll(pageNumber);
    //     // then
    //     Assertions.assertNotNull(actualResult.content().size());
    //     for(int i = 0 ; i < actualResult.content().size(); i++){
            
    //         Assertions.assertEquals(feedbackPage.getContent().get(i).getDateOfCreation(),actualResult.content().get(0).dateOfCreation());
    //     }
    // }

    // @Test
    // void testFilterFeedback() {
    //     // given
    //     // mock the calls
    //     // where
    //     // then
    // }

    @Test
    void testGetAverageRating() {
        // given
        double expectedAvg = 3;
        // mock the calls
        Mockito.when(feedbackRepository.getAvgRating()).thenReturn(expectedAvg);
        // where
        short actualAvg = feedbackDisplayService.getAverageRating();
        // then
        Assertions.assertEquals(expectedAvg, actualAvg);
    }

    private List<Feedback> createFeedback() {
        Flight flight = Flight.builder()
                .arrivalDate(LocalDate.of(2024, 12, 5))
                .departureDate(LocalDate.of(2024, 12, 5))
                .availableBusinessSeats(20)
                .availableEconomySeats(30)
                .businessPrice(200)
                .economyPrice(50)
                .build();
        String lastName = "Osama";
        String firstName = "Ahmed";
        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
        List<Feedback> feedbackList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Feedback feedback = Feedback.builder()
                    .user(user)
                    .flight(flight)
                    .cleanliness(QualityRating.FAIR)
                    .comfort(QualityRating.EXCELLENT)
                    .service(QualityRating.EXCELLENT)
                    .punctuality(QualityRating.EXCELLENT)
                    .foodAndBeverage(QualityRating.EXCELLENT)
                    .dateOfCreation(LocalDateTime.now())
                    .stars((short)5)
                    .build();
            feedbackList.add(feedback);
        }
        return feedbackList;
    }

}
