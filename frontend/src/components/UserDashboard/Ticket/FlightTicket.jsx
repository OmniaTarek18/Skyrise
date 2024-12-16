import React from 'react';
import { FaPlaneDeparture, FaPlaneArrival } from 'react-icons/fa';
import './FlightTicket.css';
import TicketHeader from './TicketHeader';
import FlightInfo from './FlightInfo';

const FlightTicket = ({
  source,
  destination,
  departureDate,
  departureTime,
  arrivalDate,
  arrivalTime,
  seatclass,
  reservedSeats
}) => {
  return (
      <div className="ticket">
        <TicketHeader />
        
        <div className="ticket-body">
          <div className="flight-route">
            <div className="route-point">
              <FaPlaneDeparture className="plane-icon" />
              <h6>{source}</h6>
              <FlightInfo date={departureDate} time={departureTime} />
            </div>
            
            <div className="flight-duration">
              <div className="duration-line">
                <div className="airplane-line"></div>
              </div>
            </div>
            
            <div className="route-point">
              <FaPlaneArrival className="plane-icon" />
              <h6>{destination}</h6>
              <FlightInfo date={arrivalDate} time={arrivalTime} />
            </div>
          </div>
          
          <div className="ticket-details">
            <div className="detail-item">
              <span className="label">Class</span>
              <span className="value">{seatclass}</span>
            </div>
            <div className="detail-item">
              <span className="label">Seats</span>
              <span className="value">{reservedSeats}</span>
            </div>
          </div>
        </div>
      </div>

  );
};

export default FlightTicket;