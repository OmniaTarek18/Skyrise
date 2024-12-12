package com.example.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.backend.Entities.Reservation;
import com.example.backend.Entities.CompositeKeys.ReservationPK;

public interface ReservationRepository extends JpaRepository<Reservation, ReservationPK>, JpaSpecificationExecutor<Reservation> {
    boolean existsByUserIdAndFlightId(Integer userId, Integer flightId);
}
