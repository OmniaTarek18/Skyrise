import React from 'react';
import './FlightPath.css';

const FlightPath = () => {
  return (
    <div className="flight-path2">
      <div className="path-line">
        <svg viewBox="0 0 100 30">
          <path d="M0,15 C30,15 70,15 100,15" />
        </svg>
      </div>
    </div>
  );
};

export default FlightPath;