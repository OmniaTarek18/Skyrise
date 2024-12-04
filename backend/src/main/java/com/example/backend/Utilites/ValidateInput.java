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

    public static void validateId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID: ID must be a positive number.");
        }
    }

    public static void validateSource (String source) {
        if (!source.contains(", ")) {
            throw new IllegalArgumentException("Invalid source format. Expected 'City, Country'.");
        }
    }

    public static void validateDestination (String destination) {
        if (!destination.contains(", ")) {
            throw new IllegalArgumentException("Invalid source format. Expected 'City, Country'.");
        }
    }
}
