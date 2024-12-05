package com.example.backend.Controllers.AdminDashboard;

import org.junit.jupiter.api.Test;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.backend.Services.FlightDisplayService;

@WebMvcTest(FlightDislplayController.class)
public class FlightDislplayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightDisplayService flightService;
    
=======

public class FlightDislplayControllerTest {
>>>>>>> feat-SCRUM-45-Admin-Dashboard-Frontend
    @Test
    void testFilter() {

    }

    @Test
    void testGetFlightsByDepartureDate() {

    }
}
