package com.example.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.backend.Entities.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer>, JpaSpecificationExecutor<Airport> {  

}
