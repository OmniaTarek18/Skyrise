package com.example.backend.DTOs;

import java.time.LocalDate;

import com.example.backend.Enums.Gender;
import com.example.backend.Enums.MealSpecification;
import com.example.backend.Enums.SpecialNeeds;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PassengerDTO(
    @NotBlank(message = "Country code cannot be blank.")
    String countryCode,
    @NotBlank(message = "Phone number cannot be blank.")
    String phoneNumber,
    @NotBlank(message = "National ID cannot be blank.")
    String nationalId,
    @NotNull(message = "Date of birth cannot be null.")
    LocalDate dateOfBirth,
    @NotBlank(message = "First name cannot be blank.")
    String firstName,
    @NotBlank(message = "Last name cannot be blank.")
    String lastName,
    @NotNull(message = "Gender cannot be null.")
    Gender gender,
    @NotBlank(message = "Passport number cannot be blank.")
    String passportNumber,
    @NotBlank(message = "Passport issuing country cannot be blank.")
    String passportIssuingCountry,
    SpecialNeeds specialNeeds,
    MealSpecification mealSpecification) {
    
}
