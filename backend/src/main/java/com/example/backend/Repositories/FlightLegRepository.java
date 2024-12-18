package com.example.backend.Repositories;


import com.example.backend.Entities.FlightLeg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightLegRepository extends JpaRepository<FlightLeg, Integer> {

}
