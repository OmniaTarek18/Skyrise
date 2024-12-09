package com.example.backend.Utilites;

import java.time.LocalDate;

public class ValidateInput {
    public static void validatePageNumber(int pageNumber) {
        if (pageNumber < 0)
            throw new IllegalArgumentException("Page number can't be negative");
    }

    public static void validateNumberOfTickets(int numberOfTickets) {
        if (numberOfTickets < 1)
            throw new IllegalArgumentException("Number of tickets can't less than or equal zero");
    }

    public static void validateDepartureDate(LocalDate input) {
        if (input == null)
            throw new NullPointerException("Departure date can't be null");
    }
}
