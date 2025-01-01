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
import { cancelFlight, updateFlight } from "../../../api/flightsAPI";

const FlightDetails = ({ open, handleClose, legs, info }) => {
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

  const handleCancellation = async (flightId) => {
    try {
      await cancelFlight(flightId);
      handleClose();
    } catch (error) {
      console.error("Failed to cancel that flight:", error);
    }
  };
  const handleUpdateFlight = async (flightId) => {
    try {
      console.log(flightLegs)
      const body = {
        id: flightId,
        arrivalDate: info.arrivalDate,
        departureDate: info.departureDate,
        flightLegs: flightLegs.map((leg) => ({
          flightLegId: leg.flightLegId,
          departureTime: leg.departureTime,
          arrivalTime: leg.arrivalTime,
          departureAirportId: leg.departureAirportId,
          arrivalAirportId: leg.arrivalAirportId,
        })),
      };
      console.log(body);
      await updateFlight(body);
      handleClose();
    } catch (error) {
      console.error("Failed to update that flight:", error);
    }
  };
  const handleCancel = () => {
    if (window.confirm("Are you sure you want to cancel this flight ?")) {
      // fetch api and cancel the flight
      handleCancellation(legs[0].flightId);
    }
  };

  const toggleEditable = () => {
    if (editable) {
      // fetch api to save
      handleUpdateFlight(legs[0].flightId);
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
