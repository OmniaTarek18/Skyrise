package com.example.backend.Controllers.AdminDashboard;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.backend.DTOs.FeedbackDTO;
import com.example.backend.DTOs.AdminDashboard.FeedbackFilterCriteria;
import com.example.backend.DTOs.PageResponse;
import com.example.backend.Enums.QualityRating;
import com.example.backend.Services.AdminDashboard.FeedbackDisplayService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebMvcTest(FeedbackDisplayController.class)
public class FeedbackDisplayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeedbackDisplayService feedbackDisplayService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAverageRating() throws Exception {
        // given
        short avg = 3;
        // mock calls
        Mockito.when(feedbackDisplayService.getAverageRating()).thenReturn(avg);
        // when
        ResultActions response = mockMvc.perform(get("/admin/average-rating"));
        // then
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(String.valueOf(avg)));
    }

    @Test
    void testGetAllFeedbackWithEmptyPage() throws Exception {
        PageResponse<FeedbackDTO> pageResponse = new PageResponse<>(Collections.emptyList(), 0, 0, 0);

        // Mock calls
        Mockito.when(feedbackDisplayService.getAll(0)).thenReturn(pageResponse);
        // when
        mockMvc.perform(get("/admin/feedback").param("pageNumber", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPages").value(0))
                .andExpect(jsonPath("$.totalElements").value(0))
                .andExpect(jsonPath("$.currentPage").value(0))
                .andExpect(jsonPath("$.content").isEmpty());
        // then
        Mockito.verify(feedbackDisplayService, Mockito.times(1)).getAll(0);
    }

    @Test
    void testGetAllFeedbackCorrectence() throws Exception {
        List<FeedbackDTO> feedbackList = createDtos();
        PageResponse<FeedbackDTO> pageResponse = new PageResponse<>(feedbackList, 4, 1, 0);

        // Mock calls
        Mockito.when(feedbackDisplayService.getAll(0)).thenReturn(pageResponse);
        // when
        mockMvc.perform(get("/admin/feedback").param("pageNumber", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(4))
                .andExpect(jsonPath("$.currentPage").value(0))
                .andExpect(jsonPath("$.content").isNotEmpty());
        // then
        Mockito.verify(feedbackDisplayService, Mockito.times(1)).getAll(0);
    }

    @Test
    void shouldFilterFeedback() throws Exception {
        // Mock call
        FeedbackFilterCriteria criteria = new FeedbackFilterCriteria((short) 2, null, null, null, null, null, null);
        PageResponse<FeedbackDTO> pageResponse = new PageResponse<>(Collections.emptyList(), 0, 0, 0);
        Mockito.when(feedbackDisplayService.filterFeedback(criteria, 0)).thenReturn(pageResponse);

        // when
        mockMvc.perform(post("/admin/feedback")
                .contentType(MediaType.APPLICATION_JSON)
                .param("pageNumber", "0")
                .content(objectMapper.writeValueAsString(criteria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPages").value(0))
                .andExpect(jsonPath("$.currentPage").value(0))
                .andExpect(jsonPath("$.content").isEmpty());

        // then
        Mockito.verify(feedbackDisplayService, Mockito.times(1)).filterFeedback(criteria, 0);
    }

    private List<FeedbackDTO> createDtos() {
        List<FeedbackDTO> feedbackList = new ArrayList<>();
        Integer userId = 1;
        Integer flightId = 2;
        short stars = 2;
        String userName = "Ahmed";
        String departureCity = "Cairo";
        String arrivalCity = "London";
        String comment = "It was a good flight";
        LocalDate departureDate = LocalDate.of(2024, 12, 5);
        QualityRating cleanliness = QualityRating.EXCELLENT;
        QualityRating comfort = QualityRating.POOR;
        QualityRating service = QualityRating.FAIR;
        QualityRating punctuality = QualityRating.EXCELLENT;
        QualityRating foodAndBeverage = QualityRating.EXCELLENT;
        for (int i = 0; i < 4; i++) {
            FeedbackDTO feedbackDTO = new FeedbackDTO(
                    userId,
                    flightId,
                    stars,
                    comment,
                    userName,
                    departureCity,
                    arrivalCity,
                    departureDate,
                    comfort,
                    service,
                    punctuality,
                    foodAndBeverage,
                    cleanliness,
                    LocalDateTime.now());
            feedbackList.add(feedbackDTO);
        }
        return feedbackList;
    }
}