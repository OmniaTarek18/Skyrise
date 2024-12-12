package com.example.backend.Specifications;

import java.time.LocalDate;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.example.backend.Entities.Airport;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.FlightLeg;
import com.example.backend.Entities.Reservation;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

public class ReservationSpecifications {
    public static Specification<Reservation> containsUserId(Integer userId) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("userId"), userId);
        };
    }

    public static Specification<Reservation> containsSource(String source) {
        return (root, query, criteriaBuilder) -> {

            Join<Reservation, FlightLeg> flightLegJoin = root.join("flight").join("flightLegs");
            Join<FlightLeg, Airport> airportJoin = flightLegJoin.join("departureAirport");

            return criteriaBuilder.and(
                    criteriaBuilder.equal(flightLegJoin.get("flightLegId"), 1),
                    criteriaBuilder.equal(airportJoin.get("airportCity"), source)
            );
        };
    }

    public static Specification<Reservation> containsDestination(String destination) {
        return (root, query, criteriaBuilder) -> {

            Join<Reservation, Flight> flightJoin = root.join("flight");

            Subquery<Integer> subquery = query.subquery(Integer.class);
            Root<FlightLeg> subqueryRoot = subquery.from(FlightLeg.class);

            subquery.select(criteriaBuilder.max(subqueryRoot.get("flightLegId")))
                    .where(criteriaBuilder.equal(subqueryRoot.get("flightId"), flightJoin.get("id")))
                    .groupBy(subqueryRoot.get("flightId"));

            Join<Flight, FlightLeg> flightLegJoin = flightJoin.join("flightLegs");
            Join<FlightLeg, Airport> airportJoin = flightLegJoin.join("arrivalAirport");

            return criteriaBuilder.and(
                    criteriaBuilder.equal(flightLegJoin.get("flightLegId"), subquery),
                    criteriaBuilder.equal(airportJoin.get("airportCity"), destination)
            );
        };
    }

    public static Specification<Reservation> containsDepartureDate(LocalDate departureDate) {
        return (root, query, criteriaBuilder) -> {

            var flightJoin = root.join("flight");

            return criteriaBuilder.equal(flightJoin.get("departureDate"), departureDate);
        };
    }

    public static Specification<Reservation> sortedByDepartureDate(String direction) {
        return (root, query, criteriaBuilder) -> {

            var flightJoin = root.join("flight");

            if (direction != null && !direction.isEmpty()) {
                Sort.Direction sortDirection = Sort.Direction.fromString(direction);
                query.orderBy(sortDirection == Sort.Direction.ASC
                        ? criteriaBuilder.asc(flightJoin.get("departureDate"))
                        : criteriaBuilder.desc(flightJoin.get("departureDate")));
            }

            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Reservation> sortedByArrivalDate(String direction) {
        return (root, query, criteriaBuilder) -> {

            var flightJoin = root.join("flight");

            if (direction != null && !direction.isEmpty()) {
                Sort.Direction sortDirection = Sort.Direction.fromString(direction);
                query.orderBy(sortDirection == Sort.Direction.ASC
                        ? criteriaBuilder.asc(flightJoin.get("arrivalDate"))
                        : criteriaBuilder.desc(flightJoin.get("arrivalDate")));
            }

            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Reservation> sortedByFlightId(String direction) {
        return (root, query, criteriaBuilder) -> {

            if (direction != null && !direction.isEmpty()) {
                Sort.Direction sortDirection = Sort.Direction.fromString(direction);
                query.orderBy(sortDirection == Sort.Direction.ASC
                        ? criteriaBuilder.asc(root.get("flightId"))
                        : criteriaBuilder.desc(root.get("flightId")));
            }

            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Reservation> containsArrivalDate(LocalDate arrivalDate) {
        return (root, query, criteriaBuilder) -> {

            var flightJoin = root.join("flight", JoinType.INNER);

            return criteriaBuilder.equal(flightJoin.get("arrivalDate"), arrivalDate);
        };
    }

    public static Specification<Reservation> containsFlightId(Integer flightId) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("flightId"), flightId);
        };
    }
}