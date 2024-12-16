import React from 'react';
import { FaPlane } from 'react-icons/fa';

const TicketHeader = () => {
  
  return (
    <div className="ticket-header">
      <div className="airline-logo">
        <FaPlane className="logo-icon" />
        <span className="airline-name">Skyrise Flights</span>
      </div>
    </div>
  );
};

export default TicketHeader;