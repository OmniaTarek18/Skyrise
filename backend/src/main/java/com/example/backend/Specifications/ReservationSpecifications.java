package com.example.backend.Specifications;

import java.time.LocalDate;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.example.backend.Entities.Airport;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.FlightLeg;
import com.example.backend.Entities.Reservation;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

public class ReservationSpecifications {
    public static Specification<Reservation> containsId(String field, Integer id) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(field), id);
        };
    }

    public static Specification<Reservation> containsLocation(String field, String location) {
        return (root, query, criteriaBuilder) -> {

            Join<Reservation, Flight> flightJoin = root.join("flight");
            Join<Flight, FlightLeg> flightLegJoin = flightJoin.join("flightLegs");
            Join<FlightLeg, Airport> airportJoin = flightLegJoin.join(field);
            Expression<?> flightLegIdCondition;

            if (field.equals("arrivalAirport")) {
                if (query == null) {
                    throw new IllegalArgumentException("Query parameter cannot be null");
                }

                flightLegIdCondition = createMaxFlightLegIdSubquery(query, criteriaBuilder, flightJoin);
            }
            else {
                flightLegIdCondition = criteriaBuilder.literal(1);
            }

            return criteriaBuilder.and(
                    criteriaBuilder.equal(flightLegJoin.get("flightLegId"), flightLegIdCondition),
                    criteriaBuilder.equal(airportJoin.get("airportCity"), location)
            );
        };
    }

    public static Specification<Reservation> containsDate(String field, LocalDate date) {
        return (root, query, criteriaBuilder) -> {

            var flightJoin = root.join("flight");

            return criteriaBuilder.equal(flightJoin.get(field), date);
        };
    }

    public static Specification<Reservation> beforeDate(String field, LocalDate date) {
        return (root, query, criteriaBuilder) -> {
            
            var flightJoin = root.join("flight");

            return criteriaBuilder.lessThan(flightJoin.get(field), date);
        };
    }
    
    public static Specification<Reservation> afterOrEqualDate(String field, LocalDate date) {
        return (root, query, criteriaBuilder) -> {
            
            var flightJoin = root.join("flight");

            return criteriaBuilder.greaterThanOrEqualTo(flightJoin.get(field), date);
        };
    }

    public static Specification<Reservation> sortedByDate(String field, String direction) {
        return (root, query, criteriaBuilder) -> {

            var flightJoin = root.join("flight");

            if (direction != null && !direction.isEmpty()) {
                Sort.Direction sortDirection = Sort.Direction.fromString(direction);

                if (query == null) {
                    throw new IllegalArgumentException("Query parameter cannot be null");
                }

                query.orderBy(sortDirection == Sort.Direction.ASC
                        ? criteriaBuilder.asc(flightJoin.get(field))
                        : criteriaBuilder.desc(flightJoin.get(field)));
            }

            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Reservation> sortedByFlightId(String direction) {
        return (root, query, criteriaBuilder) -> {

            if (direction != null && !direction.isEmpty()) {
                Sort.Direction sortDirection = Sort.Direction.fromString(direction);

                if (query == null) {
                    throw new IllegalArgumentException("Query parameter cannot be null");
                }

                query.orderBy(sortDirection == Sort.Direction.ASC
                        ? criteriaBuilder.asc(root.get("flightId"))
                        : criteriaBuilder.desc(root.get("flightId")));
            }

            return criteriaBuilder.conjunction();
        };
    }

    private static Subquery<Integer> createMaxFlightLegIdSubquery(CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, Join<Reservation, Flight> flightJoin) {
        Subquery<Integer> subquery = query.subquery(Integer.class);
        Root<FlightLeg> subqueryRoot = subquery.from(FlightLeg.class);

        subquery.select(criteriaBuilder.max(subqueryRoot.get("flightLegId")))
                .where(criteriaBuilder.equal(subqueryRoot.get("flightId"), flightJoin.get("id")))
                .groupBy(subqueryRoot.get("flightId"));

        return subquery;
    }
}