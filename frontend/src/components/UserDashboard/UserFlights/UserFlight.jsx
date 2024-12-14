import React from "react";
import { FaPlaneDeparture, FaPlaneArrival } from "react-icons/fa";
import "./userflight.css";

const UserFlight = ({ flight, onShowDetails }) => {
  return (
    <div className="flight-card">
      <div className="flight-section">
        <FaPlaneDeparture className="icon" />
        <h2>{flight.departureTime}</h2>
        <p>{flight.source}</p>
        <p className="date">{flight.departureDate}</p>
      </div>

      <div className="flight-middle">
        <div className="duration">
          <span>🕒</span>
          <p>Duration: 8h</p>
        </div>
        <div className="arrow">→</div>
      </div>

      <div className="flight-section">
        <FaPlaneArrival className="icon" />
        <h2>{flight.arrivalTime}</h2>
        <p>{flight.destination}</p>
        <p className="date">{flight.arrivalDate}</p>
      </div>

      <div className="price-section">
        <h3>L.E. {flight.price}</h3>
        <button className="book-button">Book</button>
        {/* show details button for viewing all flight legs */}
        <button
          className="details-button"
          onClick={() => onShowDetails(flight.id)}
        >
          Show Details
        </button>
      </div>
    </div>
  );
};

export default UserFlight;
