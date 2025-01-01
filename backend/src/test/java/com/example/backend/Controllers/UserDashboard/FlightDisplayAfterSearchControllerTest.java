package com.example.backend.Controllers.UserDashboard;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.example.backend.DTOs.BookingDTOs.FlightSearchCriteria;
import com.example.backend.DTOs.BookingDTOs.FlightSearchResponse;
import com.example.backend.Enums.FlightType;
import com.example.backend.Enums.SeatClass;
import com.example.backend.Controllers.BookingProcess.FlightSearchController;
import com.example.backend.DTOs.PageResponse;
import com.example.backend.Services.BookingProcess.FlightSearchService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

@WebMvcTest(controllers = FlightSearchController.class)
public class FlightDisplayAfterSearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightSearchService flightSearchService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {

        objectMapper.findAndRegisterModules();
    }

    @Test
    void testValidFlightSearch() throws Exception {
        FlightSearchCriteria validCriteria = new FlightSearchCriteria(
                1, 2, LocalDate.now(), SeatClass.ECONOMY, 1, FlightType.DIRECT, "price", "asc");

        FlightSearchResponse flight1 = new FlightSearchResponse(1, LocalDate.now(), LocalDate.now(),
                LocalTime.of(10, 30), LocalTime.of(8, 30), 150.0f);

        PageResponse<FlightSearchResponse> mockPageResponse = new PageResponse<>(
                List.of(flight1), 1L, 1, 1);

        when(flightSearchService.getFlights(any(FlightSearchCriteria.class), anyInt())).thenReturn(mockPageResponse);

        mockMvc.perform(post("/user/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validCriteria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].price").value(150.0f));
    }

    @Test
    void testInvalidFlightSearchMissingFields() throws Exception {
        FlightSearchCriteria invalidCriteria = new FlightSearchCriteria(
                null, 2, LocalDate.now(), SeatClass.ECONOMY, 1, FlightType.DIRECT, "price", "asc");

        mockMvc.perform(post("/user/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidCriteria)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testFlightSearchNoResults() throws Exception {
        FlightSearchCriteria validCriteria = new FlightSearchCriteria(
                1, 2, LocalDate.now(), SeatClass.ECONOMY, 1, FlightType.DIRECT, "price", "asc");

        PageResponse<FlightSearchResponse> emptyResponse = new PageResponse<>(
                Collections.emptyList(), 1L, 1, 0);

        when(flightSearchService.getFlights(any(FlightSearchCriteria.class), anyInt())).thenReturn(emptyResponse);

        mockMvc.perform(post("/user/search") 
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validCriteria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isEmpty());
    }

    @Test
    void testFlightSearchInvalidPageNumber() throws Exception {
        FlightSearchCriteria validCriteria = new FlightSearchCriteria(
                1, 2, LocalDate.now(), SeatClass.ECONOMY, 1, FlightType.DIRECT, "price", "asc");

        mockMvc.perform(post("/user/search?pageNumber=-1") 
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validCriteria)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testFlightSearchEdgeCaseHighPageNumber() throws Exception {
        FlightSearchCriteria validCriteria = new FlightSearchCriteria(
                1, 2, LocalDate.now(), SeatClass.ECONOMY, 1, FlightType.DIRECT, "price", "asc");

        PageResponse<FlightSearchResponse> emptyResponse = new PageResponse<>(
                Collections.emptyList(), 999L, 1, 0);

        when(flightSearchService.getFlights(any(FlightSearchCriteria.class), anyInt())).thenReturn(emptyResponse);

        mockMvc.perform(post("/user/search?pageNumber=999") 
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validCriteria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isEmpty());
    }
    
    @Test
    void testInvalidDepartureDate() throws Exception {
        FlightSearchCriteria invalidCriteria = new FlightSearchCriteria(
                1, 2, LocalDate.now().minusDays(1), SeatClass.ECONOMY, 1, FlightType.DIRECT, "price", "asc");

        mockMvc.perform(post("/user/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        invalidCriteria)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    void testInvalidSeatClass() throws Exception {
        FlightSearchCriteria invalidCriteria = new FlightSearchCriteria(
                1, 2, LocalDate.now(), null, 1, FlightType.DIRECT, "price", "asc");

        mockMvc.perform(post("/user/search") 
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        invalidCriteria)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    void testInvalidNumberOfTickets() throws Exception {
        FlightSearchCriteria invalidCriteria = new FlightSearchCriteria(
                1, 2, LocalDate.now(), SeatClass.ECONOMY, 0, FlightType.DIRECT, "price", "asc");

        mockMvc.perform(post("/user/search") 
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidCriteria)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testMissingArrivalAirportId() throws Exception {
        FlightSearchCriteria invalidCriteria = new FlightSearchCriteria(
                null, 2, LocalDate.now(), SeatClass.ECONOMY, 1, FlightType.DIRECT, "price", "asc");

        mockMvc.perform(post("/user/search") 
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidCriteria)))
                .andExpect(status().isBadRequest());
    }
    

}
