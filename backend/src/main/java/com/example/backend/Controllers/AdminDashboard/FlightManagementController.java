package com.example.backend.Controllers.AdminDashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.backend.Services.FlightManagmentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class FlightManagementController {
    private final FlightManagmentService flightManagmentService;

    @DeleteMapping("/flights/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable int id) {
        flightManagmentService.deleteFlight(id);
        return ResponseEntity.ok("The flight has been deleted successfully.");
    }
}