package com.example.backend.Specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.example.backend.Entities.Flight;


public class FlightSpecifications {

    public static Specification<Flight> containsSource(String source) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("source"), source);
    }

    public static Specification<Flight> containsDestination(String destination) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("destination"), destination);
    }

    public static Specification<Flight> containsStatus(String status) {
        if ("incomplete".equals(status))
            return containsStatusIncomplete();
        else if ("complete".equals(status))
            return containsStatusComplete();
        else 
            return containsStatusCancel();
    }

    public static Specification<Flight> containsStatusIncomplete() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(
                criteriaBuilder.greaterThan(root.get("availableBusinessSeats"), 0),
                criteriaBuilder.greaterThan(root.get("availableEconomySeats"), 0));
    }

    public static Specification<Flight> containsStatusCancel() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isCancel"), true);
    }

    public static Specification<Flight> containsStatusComplete() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get("availableBusinessSeats"), 0),
                criteriaBuilder.equal(root.get("availableEconomySeats"), 0));
    }

    public static Specification<Flight> hasDepartureDate(LocalDate departureDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("departureDate"), departureDate);
    }
}
