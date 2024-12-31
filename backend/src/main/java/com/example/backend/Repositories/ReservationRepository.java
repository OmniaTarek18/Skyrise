package com.example.backend.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.backend.Entities.Reservation;
import com.example.backend.Entities.CompositeKeys.ReservationPK;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, ReservationPK>, JpaSpecificationExecutor<Reservation> {
    
    boolean existsByUserIdAndFlightId(Integer userId, Integer flightId);

    Optional<Reservation> findByFlightIdAndUserId(Integer flightId, Integer userId);
    
    List<Reservation> findAllByUserId(Integer userId);
}
