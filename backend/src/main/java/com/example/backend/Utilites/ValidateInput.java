package com.example.backend.Utilites;

import java.time.LocalDate;

import com.example.backend.Enums.SeatClass;

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

    public static void validateSeatClass(SeatClass seatClass) {
        if (seatClass == null) {
            throw new IllegalArgumentException("SeatClass cannot be null.");
        }
    }

    public static void validateNumOfSeats(int reservedSeats) {
        if (reservedSeats < 1) {
            throw new IllegalArgumentException("Number of reserved seats is less than 1.");
        }
    }
}
