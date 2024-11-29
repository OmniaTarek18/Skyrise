package com.example.backend.DTOs;

import com.example.backend.Enums.QualityRating;
import com.example.backend.Enums.Stars;

public record FeedbackFilterCriteria(
        Stars stars,
        String direction,
        QualityRating comfort,
        QualityRating service,
        QualityRating punctuality,
        QualityRating foodAndBeverage,
        QualityRating cleanliness) {

}
