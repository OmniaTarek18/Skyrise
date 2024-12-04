package com.example.backend.SignUpLogIn;

import com.example.Controllers.SignUpLogInModuleController;
import com.example.Entities.Customer;
import com.example.Services.SignUpLogInModuleServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SignUpLogInModuleController.class)
class SignUpLogInModuleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SignUpLogInModuleServices signUpLogInModuleServices;

    @Test
    void testSignUp_withValidData_shouldReturn201() throws Exception {

        Date dNow = new Date();
        Customer customer = new Customer("123", "010", "1", dNow, "abc", "db", true, "123", "1", "test@example.com", "password123");
        when(signUpLogInModuleServices.addCustomer(any(Customer.class))).thenReturn(true);

        mockMvc.perform(post("/signUp/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(customer)))
                .andExpect(status().isCreated()) ;
    }


    @Test
    void testSignUp_withDuplicateEmail_shouldReturn422() throws Exception {
        Date dNow = new Date();
        Customer customer = new Customer("123", "010", "1", dNow, "abc", "db", true, "123", "1", "test@example.com", "password123");

        // Simulate the first customer registration (email not existing yet)
        when(signUpLogInModuleServices.addCustomer(any(Customer.class))).thenReturn(true);
        mockMvc.perform(post("/signUp/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(customer)))
                .andExpect(status().isCreated());


        when(signUpLogInModuleServices.addCustomer(any(Customer.class))).thenReturn(false);

        mockMvc.perform(post("/signUp/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(customer)))
                .andExpect(status().isUnprocessableEntity()) ;
    }

    @Test
    void testSignUp_withInvalidData_shouldReturn422() throws Exception {
        // Arrange
        Customer customer = new Customer("", "", "", new Date(), "", "", true, "", "", "", "");

        // Act & Assert
        mockMvc.perform(post("/signUp/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(customer)))
                .andExpect(status().isUnprocessableEntity());
    }


    }
