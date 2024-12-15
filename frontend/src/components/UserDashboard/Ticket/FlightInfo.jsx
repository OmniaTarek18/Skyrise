import React from 'react';
import { formatDate, formatTime } from './dateUtils';

const FlightInfo = ({ date, time, label }) => {
  return (
    <div className="flight-info">
      <span className="info-label">{label}</span>
      <div className="info-details">
        <span className="ticket-date">{formatDate(date)}</span>
        <span className="ticket-time">{formatTime(time)}</span>
      </div>
    </div>
  );
};

export default FlightInfo;