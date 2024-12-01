package com.example.backend.Specifications;

import java.time.LocalDate;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.example.backend.Entities.FlightLeg;
import com.example.backend.Entities.Reservation;
import com.example.backend.Utilites.ValidateInput;

import jakarta.persistence.criteria.JoinType;

public class ReservationSpecifications {
    public static Specification<Reservation> containsUserId(Integer userId) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("userId"), userId);
        };
    }

    public static Specification<Reservation> containsSource(String source) {
        return (root, query, criteriaBuilder) -> {

            if (source == null || source.trim().isEmpty()) {
                return criteriaBuilder.conjunction(); // No filtering applied
            }

            ValidateInput.validateSource(source);

            String[] s = source.split(", ");
            String city = s[0];
            String country = s[1];

            var flightLegJoin = root.join("flight").join("flightLegs");

            var firstLegPredicate = criteriaBuilder.equal(flightLegJoin.get("flightLegId"), 1);

            var airportJoin = flightLegJoin.join("departureAirport");
            var cityPredicate = criteriaBuilder.equal(airportJoin.get("airportCity"), city);
            var countryPredicate = criteriaBuilder.equal(airportJoin.get("airportCountry"), country);

            return criteriaBuilder.and(firstLegPredicate, cityPredicate, countryPredicate);
        };

    }

    public static Specification<Reservation> containsDestination(String destination) {
        return (root, query, criteriaBuilder) -> {

            if (destination == null || destination.trim().isEmpty()) {
                return criteriaBuilder.conjunction(); // No filtering applied
            }

            ValidateInput.validateDestination(destination);

            String[] d = destination.split(", ");
            String city = d[0];
            String country = d[1];

            var flightJoin = root.join("flight");
            var flightLegJoin = flightJoin.join("flightLegs");

            var subquery = query.subquery(Integer.class);
            var subqueryRoot = subquery.from(FlightLeg.class);
            subquery.select(criteriaBuilder.max(subqueryRoot.get("flightLegId")))
                    .where(criteriaBuilder.equal(subqueryRoot.get("flightId"), flightJoin.get("id")));

            var lastLegPredicate = criteriaBuilder.equal(flightLegJoin.get("flightLegId"), subquery);

            var airportJoin = flightLegJoin.join("arrivalAirport");
            var cityPredicate = criteriaBuilder.equal(airportJoin.get("airportCity"), city);
            var countryPredicate = criteriaBuilder.equal(airportJoin.get("airportCountry"), country);

            return criteriaBuilder.and(lastLegPredicate, cityPredicate, countryPredicate);
        };

    }

    public static Specification<Reservation> containsDepartureDate (LocalDate departureDate) {
        return (root, query, criteriaBuilder) -> {

            var flightJoin = root.join("flight");

            return criteriaBuilder.equal(flightJoin.get("departureDate"), departureDate);
        };
    }

    public static Specification<Reservation> sortedByDepartureDate (String direction) {
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

    public static Specification<Reservation> containsArrivalDate (LocalDate arrivalDate) {
        return (root, query, criteriaBuilder) -> {

            var flightJoin = root.join("flight", JoinType.INNER);

            return criteriaBuilder.equal(flightJoin.get("arrivalDate"), arrivalDate);
        };
    }

    public static Specification<Reservation> conainsFlightId (Integer flightId) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("flightId"), flightId);
        };
    }
}
