package com.example.backend.DTOs;

import java.time.LocalDateTime;

import com.example.backend.Enums.QualityRating;
import com.example.backend.Enums.Stars;

public record FeedbackDTO(
        Stars stars,
        String comment,
        String userName,
        String flightSource,
        String flightDestination,
        String flightDepartureDate,
        QualityRating comfort,
        QualityRating service,
        QualityRating punctuality,
        QualityRating foodAndBeverage,
        QualityRating cleanliness,
        LocalDateTime dateOfCreation) {
}
