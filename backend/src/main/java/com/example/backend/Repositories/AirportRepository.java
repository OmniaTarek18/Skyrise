package com.example.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.backend.Entities.Airport;

public interface AirportRepository extends JpaRepository<Airport, Integer>, JpaSpecificationExecutor<Airport> {  

}
