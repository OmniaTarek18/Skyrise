package com.example.backend.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.Entities.Reservation;
import com.example.backend.Entities.CompositeKeys.ReservationPK;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ReservationRepository extends JpaRepository<Reservation, ReservationPK>, JpaSpecificationExecutor<Reservation> {
    Page<Reservation> findByUserId(Integer userId, Pageable pageable);

    Page<Reservation> findByFlightId(Integer flightId, Pageable pageable);
}
