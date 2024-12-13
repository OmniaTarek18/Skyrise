package com.example.backend.Utilites;

import java.time.LocalDate;

public class ValidateInput {
    public static void validatePageNumber(int pageNumber) {
        if (pageNumber < 0)
            throw new IllegalArgumentException("Page number can't be negative");
    }

    public static void validateDepartureDate(LocalDate input) {
        if (input == null)
            throw new NullPointerException("Departure date can't be null");
    }
}
