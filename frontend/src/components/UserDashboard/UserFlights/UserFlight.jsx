import React, { useState } from "react";
import { FaPlaneDeparture, FaPlaneArrival } from "react-icons/fa";
import Popup from "../../shared/Popup";
import "./userflight.css";
import FlightPath from "./FlightPath";
import TicketReservationForm from "../../TicketReservationForm"


const UserFlight = ({flight, departureCity, arrivalCity, onShowDetails }) => {
  const [showTicketReservation, setShowTicketReservation] = useState(false);
  return (
    <div className="search-flight-card">
      {showTicketReservation && (
        <Popup onClose={() => setShowTicketReservation(false)}>
          <TicketReservationForm flightId={flight.id}/>
        </Popup>
      )}
      <div className="flight-section">
        <FaPlaneDeparture className="user-flight-icon" />
        <h2>{flight.departureTime}</h2>
        <p>{departureCity}</p>
        <p className="date">{flight.departureDate}</p>
      </div>

      <div className="flight-middle">
        <FlightPath />
      </div>

      <div className="flight-section">
        <FaPlaneArrival className="user-flight-icon" />
        <h2>{flight.arrivalTime}</h2>
        <p>{arrivalCity}</p>
        <p className="date">{flight.arrivalDate}</p>
      </div>

      <div className="price-section">
        <h3>L.E. {flight.price}</h3>
        <button className="book-button" onClick={() => setShowTicketReservation(true)}>
          Book
        </button>
        <a
          href="#"
          className="details-link"
          onClick={(e) => {
            e.preventDefault();
            onShowDetails(flight.id);
          }}
        >
          Show Details
        </a>
      </div>
    </div>
  );
};

export default UserFlight;
