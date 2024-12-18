package com.example.backend.Specifications;

import org.springframework.data.jpa.domain.Specification;

import com.example.backend.Entities.Feedback;
import com.example.backend.Enums.QualityRating;

public class FeedbackSpecifications {

    public static Specification<Feedback> containsStars(short stars) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("stars"), stars);
    }

    public static Specification<Feedback> containsService(QualityRating service) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("service"), service);
    }

    public static Specification<Feedback> containsComfort(QualityRating comfort) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("comfort"), comfort);
    }

    public static Specification<Feedback> containsPunctuality(QualityRating punctuality) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("punctuality"), punctuality);
    }

    public static Specification<Feedback> containsFoodAndBeverage(QualityRating foodAndBeverage) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("foodAndBeverage"), foodAndBeverage);
    }

    public static Specification<Feedback> containsCleanliness(QualityRating cleanliness) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("cleanliness"), cleanliness);
    }

}
