package com.example.backend.Services;

import com.example.backend.Entities.FlightLeg;
import com.example.backend.Repositories.FlightLegRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightLegServices {

    FlightLegRepository flightLegRepository ;

    @Autowired
    public FlightLegServices(FlightLegRepository flightLegRepository) {
        this.flightLegRepository = flightLegRepository;
    }

    public boolean addFlightLeg(FlightLeg flightLeg) {
        try {
            this.flightLegRepository.save(flightLeg);
            return true;
        } catch (Exception e) {
            return false ;
        }
    }
}
