package com.example.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.Entities.FlightLeg;
import com.example.backend.Entities.CompositeKeys.FlightLegPK;
import java.util.List;

@Repository
public interface FlightLegRepository extends JpaRepository<FlightLeg, FlightLegPK> {
    List<FlightLeg> findByFlightId(Integer flightId);
}