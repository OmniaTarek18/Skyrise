package com.example.backend.Specifications;

import java.time.LocalDate;
import org.springframework.data.jpa.domain.Specification;
import com.example.backend.Entities.Airport;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.FlightLeg;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

public class FlightSpecifications {

    public static Specification<Flight> containsSource(String source) {
        return (root, query, criteriaBuilder) -> {
            Join<Flight, FlightLeg> flightAndFlightLeg = root.join("flightLegs");
            Join<FlightLeg, Airport> flightLegAndAirport = flightAndFlightLeg.join("departureAirport");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(flightLegAndAirport.get("airportCity"), source),
                    criteriaBuilder.equal(flightAndFlightLeg.get("flightLegId"), 1));
        };
    }

    public static Specification<Flight> containsDestination(String destination) {
        return (root, query, criteriaBuilder) -> {
            Subquery<Integer> subquery = query.subquery(Integer.class);
            Root<FlightLeg> subRoot = subquery.from(FlightLeg.class);
    
            subquery.select(criteriaBuilder.greatest(subRoot.<Integer>get("flightLegId")))
                    .where(criteriaBuilder.equal(subRoot.get("flight"), root))
                    .groupBy(subRoot.get("flight"));
    
            Join<Flight, FlightLeg> flightAndFlightLeg = root.join("flightLegs");
            Join<FlightLeg, Airport> flightLegAndAirport = flightAndFlightLeg.join("arrivalAirport");
    
            return criteriaBuilder.and(
                criteriaBuilder.equal(flightAndFlightLeg.get("flightLegId"), subquery),
                criteriaBuilder.equal(flightLegAndAirport.get("airportCity"), destination)
            );
        };
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

    public static Specification<Flight> hasArrivalDateLessThan(LocalDate arrivalDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("arrivalDate"), arrivalDate);
    }
}
