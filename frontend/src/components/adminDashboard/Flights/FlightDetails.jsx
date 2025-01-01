import React, { useState, useEffect } from "react";
import {
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  Button,
  TextField,
  IconButton,
  MenuItem,
} from "@mui/material";
import CloseIcon from "@mui/icons-material/Close";
import { getCountriesAndAirportsToTravelAPI } from "../../homepage/SearchFlights/api";

const FlightDetails = ({ open, handleClose, legs }) => {
  const [flightLegs, setFlightLegs] = useState([]);
  const [locations, setLocations] = useState([]);
  const [editable, setEditable] = useState(false); // Tracks if fields are editable

  useEffect(() => {
    setFlightLegs(legs || []);
  }, [legs]);

  useEffect(() => {
    // Fetch airports when the component mounts
    fetchAirports();
  }, []);
  // useEffect(() => {
  //   console.log("Updated flightLegs:", flightLegs);
  // }, [flightLegs]);
  
  const fetchAirports = async () => {
    const data = await getCountriesAndAirportsToTravelAPI();
    if (data) {
      const locationOptions = data.map((airport) => ({
        label: `${airport.airportCountry}, ${airport.airportCity}`,
        value: airport.id,
      }));
      setLocations(locationOptions);
    }
  };

  const updateFlightLeg = (id, field, value) => {
    setFlightLegs((prevLegs) =>
      prevLegs.map((leg) =>
        leg.flightLegId === id ? { ...leg, [field]: value } : leg
      )
    );
  };

  const handleCancel = (id) => {
    if (window.confirm("Are you sure you want to cancel this flight ?")) {
      // fetch api and cancel the flight
    }
  };

  const toggleEditable = () => {
    if(editable){
      // fetch api to save
      console.log(flightLegs)
    }
    setEditable(!editable);
    
  };

  if (!open) return null;

  return (
    <Dialog open={open} onClose={handleClose} fullWidth>
      <DialogTitle>
        Flight Details
        <IconButton
          aria-label="close"
          onClick={handleClose}
          sx={{ position: "absolute", right: 8, top: 8 }}
        >
          <CloseIcon />
        </IconButton>
      </DialogTitle>
      <div className="d-flex justify-content-evenly align-items-center">
        <Button
          onClick={toggleEditable}
          variant="outlined"
          color={editable ? "secondary" : "primary"}
          className="my-3"
        >
          {editable ? "Save" : "Enable Edit"}
        </Button>
        <Button color="secondary" variant="outlined" onClick={handleCancel}>
          Cancel Flight
        </Button>
      </div>
      <DialogContent>
        {flightLegs.map((leg) => (
          <div
            key={leg.flightLegId}
            style={{
              marginBottom: "20px",
              borderBottom: "1px solid #ccc",
              paddingBottom: "10px",
            }}
          >
            <h4>Flight Leg {leg.flightLegId}</h4>
            <div style={{ display: "flex", gap: "20px" }} className="mt-3">
              <div>
                <TextField
                  label="Departure Time"
                  value={leg.departureTime}
                  onChange={(e) =>
                    updateFlightLeg(
                      leg.flightLegId,
                      "departureTime",
                      e.target.value
                    )
                  }
                  fullWidth
                  style={{ marginBottom: "10px" }}
                  disabled={!editable}
                />
                <TextField
                  select
                  label="Departure Airport"
                  value={leg.departureAirportId || ""}
                  onChange={(e) =>
                    updateFlightLeg(
                      leg.flightLegId,
                      "departureAirportId",
                      e.target.value
                    )
                  }
                  fullWidth
                  style={{ marginBottom: "10px" }}
                  disabled={!editable}
                >
                  {locations.map((location) => (
                    <MenuItem key={location.value} value={location.value}>
                      {location.label}
                    </MenuItem>
                  ))}
                </TextField>
              </div>
              <div>
                <TextField
                  label="Arrival Time"
                  value={leg.arrivalTime}
                  onChange={(e) =>
                    updateFlightLeg(
                      leg.flightLegId,
                      "arrivalTime",
                      e.target.value
                    )
                  }
                  fullWidth
                  style={{ marginBottom: "10px" }}
                  disabled={!editable}
                />
                <TextField
                  select
                  label="Arrival Airport"
                  value={leg.arrivalAirportId || ""}
                  onChange={(e) =>
                    updateFlightLeg(
                      leg.flightLegId,
                      "arrivalAirportId",
                      e.target.value
                    )
                  }
                  fullWidth
                  style={{ marginBottom: "10px" }}
                  disabled={!editable}
                >
                  {locations.map((location) => (
                    <MenuItem key={location.value} value={location.value}>
                      {location.label}
                    </MenuItem>
                  ))}
                </TextField>
              </div>
            </div>
            <div
              style={{
                display: "flex",
                justifyContent: "space-between",
                marginTop: "10px",
              }}
            ></div>
          </div>
        ))}
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose} color="primary">
          Close
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default FlightDetails;
