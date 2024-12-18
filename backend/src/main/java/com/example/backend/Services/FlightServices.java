package com.example.backend.Services;

import com.example.backend.Entities.Flight;
import com.example.backend.Repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightServices {

    private final  FlightRepository flightRepository ;

    @Autowired
    public FlightServices(FlightRepository flightRepository) {
        this.flightRepository = flightRepository ;
    }

    public int addFlight(Flight flight) {
        try {
            Flight savedFlight = this.flightRepository.save(flight);
            return savedFlight.getId() ;
        } catch (Exception e) {
            return -1 ;
        }
    }


}
