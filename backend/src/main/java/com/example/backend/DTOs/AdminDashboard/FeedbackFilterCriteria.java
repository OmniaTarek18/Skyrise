package com.example.backend.DTOs.AdminDashboard;

import com.example.backend.Enums.QualityRating;

public record FeedbackFilterCriteria(
        short stars,
        String direction,
        QualityRating comfort,
        QualityRating service,
        QualityRating punctuality,
        QualityRating foodAndBeverage,
        QualityRating cleanliness) {

}
