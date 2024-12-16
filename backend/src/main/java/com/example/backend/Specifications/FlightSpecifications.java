package com.example.backend.Specifications;

import java.time.LocalDate;
import org.springframework.data.jpa.domain.Specification;
import com.example.backend.Entities.Airport;
import com.example.backend.Entities.Flight;
import com.example.backend.Entities.FlightLeg;
import com.example.backend.Enums.FlightType;
import com.example.backend.Enums.SeatClass;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
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

            if (query == null) {
                throw new IllegalArgumentException("Query parameter cannot be null");
            }

            Subquery<Integer> subquery = createNumberOfLastFlightLegSubquery(root, query, criteriaBuilder);
            Join<Flight, FlightLeg> flightAndFlightLeg = root.join("flightLegs");
            Join<FlightLeg, Airport> flightLegAndAirport = flightAndFlightLeg.join("arrivalAirport");

            return criteriaBuilder.and(
                    criteriaBuilder.equal(flightAndFlightLeg.get("flightLegId"), subquery),
                    criteriaBuilder.equal(flightLegAndAirport.get("airportCity"), destination));
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


    public static Specification<Flight> hasDepartureAirport(Integer id) {
        return (root, query, criteriaBuilder) -> {
            Join<Flight, FlightLeg> flightAndFlightLeg = root.join("flightLegs");
            Join<FlightLeg, Airport> flightLegAndAirport = flightAndFlightLeg.join("departureAirport");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(flightLegAndAirport.get("id"), id),
                    criteriaBuilder.equal(flightAndFlightLeg.get("flightLegId"), 1));
        };
    }

    public static Specification<Flight> hasArrivalAirport(Integer id) {
        return (root, query, criteriaBuilder) -> {

            if (query == null) {
                throw new IllegalArgumentException("Query parameter cannot be null");
            }

            Subquery<Integer> subquery = createNumberOfLastFlightLegSubquery(root, query, criteriaBuilder);

            Join<Flight, FlightLeg> flightAndFlightLeg = root.join("flightLegs");
            Join<FlightLeg, Airport> flightLegAndAirport = flightAndFlightLeg.join("arrivalAirport");

            return criteriaBuilder.and(
                    criteriaBuilder.equal(flightAndFlightLeg.get("flightLegId"), subquery),
                    criteriaBuilder.equal(flightLegAndAirport.get("id"), id));
        };
    }

    public static Specification<Flight> hasAvailableSeats(SeatClass seatClass, int numberOfTickets) {
        if (seatClass == SeatClass.ECONOMY)
            return hasSeats("availableEconomySeats", numberOfTickets);
        else
            return hasSeats("availableBusinessSeats", numberOfTickets);
    }

    public static Specification<Flight> hasSeats(String attributeName, int numberOfTickets) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(
                root.get(attributeName),
                numberOfTickets);
    }

    public static Specification<Flight> hasFlightType(FlightType flightType) {
        if (flightType == FlightType.DIRECT)
            return haslegs(true);
        else
            return haslegs(false);
    }

    public static Specification<Flight> haslegs(boolean isDirect) {
        return (root, query, criteriaBuilder) -> {
            Subquery<Long> subquery = createLegCountSubquery(root, query, criteriaBuilder, isDirect);
            return criteriaBuilder.exists(subquery);
        };
    }

    private static Subquery<Integer> createNumberOfLastFlightLegSubquery(Root<Flight> root, CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder) {
        Subquery<Integer> subquery = query.subquery(Integer.class);
        Root<FlightLeg> subRoot = subquery.from(FlightLeg.class);

        subquery.select(criteriaBuilder.max(subRoot.get("flightLegId")))
                .where(criteriaBuilder.equal(subRoot.get("flight"), root))
                .groupBy(subRoot.get("flight"));

        return subquery;
    }

    private static Subquery<Long> createLegCountSubquery(Root<Flight> root, CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder, boolean isDirect) {
        Subquery<Long> subquery = query.subquery(Long.class);
        Root<FlightLeg> subRoot = subquery.from(FlightLeg.class);

        subquery.select(criteriaBuilder.count(subRoot.get("flightLegId")))
                .where(criteriaBuilder.equal(subRoot.get("flight"), root))
                .groupBy(subRoot.get("flight"));

        if (isDirect)
            subquery.having(criteriaBuilder.equal(criteriaBuilder.count(subRoot.get("flightLegId")), 1));
        else
            subquery.having(criteriaBuilder.greaterThan(criteriaBuilder.count(subRoot.get("flightLegId")), (long) 1));
        return subquery;
    }
}
