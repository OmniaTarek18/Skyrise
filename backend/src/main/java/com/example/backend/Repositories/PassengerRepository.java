package com.example.backend.Repositories;

import com.example.backend.Entities.Passenger;
import com.example.backend.Entities.Reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

    List<Passenger> findByReservation(Reservation reservation);
}
