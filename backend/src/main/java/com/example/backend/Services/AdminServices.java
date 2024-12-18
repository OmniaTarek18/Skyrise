package com.example.backend.Services;


import com.example.backend.Entities.Flight;
import com.example.backend.Entities.FlightLeg;
import org.springframework.stereotype.Service;



@Service
public class AdminServices {

    private final AccountServices accountServices;
    private final FlightLegServices flightLegServices ;
    private final FlightServices flightServices ;

    public AdminServices(AccountServices accountServices, FlightLegServices flightLegServices, FlightServices flightServices) {
        this.flightServices = flightServices;
        this.accountServices = accountServices;
        this.flightLegServices = flightLegServices;
    }

    public boolean upgradeUserToAdmin(String email) {
        return this.accountServices.updateAccountFromCustomerToAdmin(email);
    }


    public int createFlight(Flight flight) {
        return this.flightServices.addFlight(flight);
    }

    public boolean attachFlightLegToAFlight(FlightLeg flightLeg) {
        return this.flightLegServices.addFlightLeg(flightLeg);
    }

}

