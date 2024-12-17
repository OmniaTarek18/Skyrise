import React from "react";
import { FaPlaneDeparture, FaPlaneArrival } from "react-icons/fa";
import "./userflight.css";
import FlightPath from "./FlightPath";

const UserFlight = ({ flight, departureCity, arrivalCity, onShowDetails }) => {
  return (
    <div className="search-flight-card">
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
        <button className="book-button">Book</button>
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
