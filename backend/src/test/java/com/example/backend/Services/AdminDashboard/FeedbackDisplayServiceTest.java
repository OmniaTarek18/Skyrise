package com.example.backend.Services.AdminDashboard;

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
import com.example.backend.Services.AdminDashboard.FeedbackDisplayService;

@ExtendWith(MockitoExtension.class)
public class FeedbackDisplayServiceTest {

    @InjectMocks
    private FeedbackDisplayService feedbackDisplayService;

    @Mock
    private FeedbackRepository feedbackRepository;

    @Test
    void testGetAverageRating() {
        // given
        double expectedAvg = 3;
        // mock the calls
        Mockito.when(feedbackRepository.getAvgRating()).thenReturn(expectedAvg);
        // when
        short actualAvg = feedbackDisplayService.getAverageRating();
        // then
        Assertions.assertEquals(expectedAvg, actualAvg);
    }


}