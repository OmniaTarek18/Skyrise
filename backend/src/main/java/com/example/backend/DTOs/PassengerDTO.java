package com.example.backend.DTOs;

import java.time.LocalDate;

import com.example.backend.Enums.Gender;
import com.example.backend.Enums.MealSpecification;
import com.example.backend.Enums.SpecialNeeds;

public record PassengerDTO(
    String countryCode,
    String phoneNumber,
    String nationalId,
    LocalDate dateOfBirth,
    String firstName,
    String lastName,
    Gender gender,
    String passportNumber,
    String passportIssuingCountry,
    SpecialNeeds specialNeeds,
    MealSpecification mealSpecification) {
    
}
