import React from "react";
import { X } from "lucide-react";
import "./DetailsPopup.css";
import FlightLeg from "../FlightLeg/FlightLeg"; 

const DetailsPopup = ({ isOpen, onClose, legs }) => {
  if (!isOpen) return null;

  return (
    <div className="details-popup-overlay" onClick={onClose}>
      <div
        className="details-popup-content"
        onClick={(e) => e.stopPropagation()}
      >
        <button className="close-button" onClick={onClose}>
          <X size={24} />
        </button>

        <h2 className="details-popup-title">Flight Details</h2>
        <div className="flight-legs-container">
          {Array.isArray(legs) && legs.length > 0 ? (
            legs.map((leg, index) => (
              <FlightLeg
                key={index}
                departureTime={leg.departureTime}
                departureAirportName={leg.departureAirportName}
                departureAirportCountry={leg.departureAirportCountry}
                departureAirportCity={leg.departureAirportCity}
                departureAirportCode={leg.departureAirportCode}
                arrivalTime={leg.arrivalTime}
                arrivalAirportName={leg.arrivalAirportName}
                arrivalAirportCountry={leg.arrivalAirportCountry}
                arrivalAirportCity={leg.arrivalAirportCity}
                arrivalAirportCode={leg.arrivalAirportCode}
              />
            ))
          ) : (
            <p>No flight details available.</p>
          )}
        </div>
      </div>
    </div>
  );
};

export default DetailsPopup;
