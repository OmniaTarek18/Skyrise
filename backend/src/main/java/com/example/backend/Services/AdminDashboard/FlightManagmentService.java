package com.example.backend.Services.AdminDashboard;

import org.springframework.stereotype.Service;

import com.example.backend.Repositories.FlightRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlightManagmentService {
    private final FlightRepository flightRepository;

    public void deleteFlight(int id) {
        if (flightRepository.existsById(id))
            flightRepository.deleteById(id);
        else
            throw new EntityNotFoundException("Flight doesn't exist");
    }
}
