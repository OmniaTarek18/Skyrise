import React, { useState } from "react";
import { Plane } from "lucide-react";
import "./flightcard.css";
import InfoButton from "./InfoButton";

export const FlightCard = ({ flight, statusStyle }) => {
  
  const isCancelled = flight.isCancelled;
  console.log(flight, "flight")
  return (
    <div className="flight-card">
      <div className="flight-header-info-icon d-flex justify-content-between align-items-center">
        <div className="flight-header-info">
          <span className="flight-number">{flight.flightNumber}</span>
          <span
            className={`flight-status ${statusStyle} ${
              isCancelled ? "cancelled" : "on-time"
            }`}
          >
            {isCancelled ? "Cancelled" : flight.status}
          </span>
        </div>
        {!isCancelled && (
          <InfoButton
            id={flight.flightNumber}
            info={{
              departureDate: flight.departureDate,
              arrivalDate: flight.arrivalDate,
            }}
          />
        )}
      </div>
      <div className="flight-card-header">
        <div className="flight-card-left">
          <div className="flight-times">
            <FlightTime time={flight.departureTime} location={flight.source} />
            <FlightPath />
            <FlightTime
              time={flight.arrivalTime}
              location={flight.destination}
            />
          </div>
        </div>

        <div className="flight-card-right">
          <p className="flight-price">
            Economy: L.E.{" "}
            {flight.economyPrice !== undefined ? flight.economyPrice : "N/A"}
          </p>
          <p className="seats-available">
            {`${flight.economySeatsAvailable} seats left`}
          </p>
        </div>
        <div className="flight-card-right">
          <p className="flight-price">
            Business: L.E.{" "}
            {flight.businessPrice !== undefined ? flight.businessPrice : "N/A"}
          </p>
          <p className="seats-available">
            {`${flight.businessSeatsAvailable} seats left`}
          </p>
        </div>
      </div>
    </div>
  );
};

const FlightTime = ({ time, location }) => (
  <div className="flight-time">
    <p className="location">{location}</p>
    <p className="time">{time}</p>
  </div>
);

const FlightPath = () => (
  <div className="flight-path">
    <div className="flight-path-line"></div>
    <Plane className="plane-icon" />
    <div className="flight-path-line"></div>
  </div>
);
