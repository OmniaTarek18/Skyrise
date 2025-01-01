import React, { useState } from "react";
import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  IconButton,
} from "@mui/material";
import InfoIcon from "@mui/icons-material/Info";
import CloseIcon from "@mui/icons-material/Close";
import FlightDetails from "./FlightDetails";
import { fetchFlightDetails } from "../../../api/flightsAfterSearch";


const InfoButton = ({id}) => {
  const [open, setOpen] = useState(false);
  const [flightId, setFlightId] = useState(id)
  const [legs, setLegs] = useState(null)

  const onShowDetails = async (flightId) => {
    try {
      const details = await fetchFlightDetails(flightId);
      console.log(details);
      setLegs(details);
      setOpen(true);

    } catch (error) {
      console.error("Error fetching flight details:", error);
    }
  };
  const handleOpen = () => {
    onShowDetails(flightId);
  };

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <div>
      {/* Button with Info Icon */}
      <Button
        variant="outlined"
        color="primary"
        startIcon={<InfoIcon />}
        onClick={handleOpen}
      >
        Info
      </Button>

      {/* Dialog (Popup Window) */}
      <FlightDetails
        open={open}
        handleClose={handleClose}
        legs={legs}
      />
    </div>
  );
};

export default InfoButton;
