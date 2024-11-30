package com.example.backend.Specifications;

import org.springframework.data.jpa.domain.Specification;

import com.example.backend.Entities.Feedback;
import com.example.backend.Enums.QualityRating;
import com.example.backend.Enums.Stars;

public class FeedbackSpecifications {

    public static Specification<Feedback> containsStars(Stars stars) {
        return (root, query, criteriaBuilder) -> stars == null ? null : criteriaBuilder.equal(root.get("stars"), stars);
    }

    public static Specification<Feedback> containsService(QualityRating service) {
        return (root, query, criteriaBuilder) -> service == null ? null : criteriaBuilder.equal(root.get("service"), service);
    }

    public static Specification<Feedback> containsComfort(QualityRating comfort) {
        return (root, query, criteriaBuilder) -> comfort == null ? null : criteriaBuilder.equal(root.get("comfort"), comfort);
    }

    public static Specification<Feedback> containsPunctuality(QualityRating punctuality) {
        return (root, query, criteriaBuilder) -> punctuality == null ? null : criteriaBuilder.equal(root.get("punctuality"), punctuality);
    }
    
    public static Specification<Feedback> containsFoodAndBeverage(QualityRating foodAndBeverage) {
        return (root, query, criteriaBuilder) -> foodAndBeverage == null ? null : criteriaBuilder.equal(root.get("foodAndBeverage"), foodAndBeverage);
    }
    
    public static Specification<Feedback> containsCleanliness(QualityRating cleanliness) {
        return (root, query, criteriaBuilder) -> cleanliness == null ? null : criteriaBuilder.equal(root.get("cleanliness"), cleanliness);
    }

}
