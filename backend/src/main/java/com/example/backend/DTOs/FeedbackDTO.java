package com.example.backend.DTOs;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.backend.Enums.QualityRating;

public record FeedbackDTO(
        Integer userId,
        Integer flightId,
        short stars,
        String comment,
        String userName,
        String flightSource,
        String flightDestination,
        LocalDate flightDepartureDate,
        QualityRating comfort,
        QualityRating service,
        QualityRating punctuality,
        QualityRating foodAndBeverage,
        QualityRating cleanliness,
        LocalDateTime dateOfCreation) {
}
